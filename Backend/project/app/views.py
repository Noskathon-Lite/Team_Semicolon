from django.http import HttpResponse, Http404
from django.contrib.auth import authenticate
from django.contrib.auth.tokens import default_token_generator
from django.contrib.sites.shortcuts import get_current_site
from rest_framework import serializers
from django.utils.http import urlsafe_base64_encode
from django.utils.http import urlsafe_base64_decode
from django.template.loader import render_to_string
from django.conf import settings
from rest_framework import status, permissions
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework.generics import ListAPIView
from rest_framework.permissions import AllowAny
from rest_framework.response import Response
from rest_framework.views import APIView
from .models import User
from rest_framework.permissions import IsAuthenticated
import os
from django.http import JsonResponse
from django.views import View
import json
from django.core.files.storage import FileSystemStorage
from datetime import date , timedelta
from .serializers import UserSerializer , UserLoginSerializer , PoliceSerializer ,PoliceLoginSerializer , CriminalSerializer , FeedbackSerializer
from rest_framework.authentication import BasicAuthentication
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator
from .models import Criminal , Feedback
from rest_framework.views import APIView
import cv2
from .face_recognition_model import process_video
from .models import ProcessedVideo


class RegisterUserView(APIView):
    authentication_classes = [BasicAuthentication]
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Account Created Successfully"},status=status.HTTP_201_CREATED,)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserLoginView(APIView):
    authentication_classes = [BasicAuthentication]
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = UserLoginSerializer(data=request.data)
        if serializer.is_valid():
            phone_number = serializer.validated_data['phone_number']
            password = serializer.validated_data['password']
            try:
                user = User.objects.get(phone_number=phone_number)
            except User.DoesNotExist:
                return Response({"message": "Invalid phone number or password"}, status=status.HTTP_401_UNAUTHORIZED)

            # Authenticate the user with the phone number and password
            if user.check_password(password):  # Use `check_password` for manual validation
                if user.user_type == 'user':  # Customize user type logic if necessary
                    refresh = RefreshToken.for_user(user)

                    user_details = {
                        "name": user.name,
                        "email": user.email,
                        "phone_number": user.phone_number,
                    }

                    return Response({
                        "message": "Login successful",
                        "user_detail": user_details,
                        "access_token": str(refresh.access_token),
                        "refresh_token": str(refresh),
                    }, status=status.HTTP_200_OK)

            return Response({"message": "Invalid phone number or password"}, status=status.HTTP_401_UNAUTHORIZED)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RegisterPoliceView(APIView):
    authentication_classes = [BasicAuthentication]
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = PoliceSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"message": "Account Created Successfully"},status=status.HTTP_201_CREATED,)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class PoliceLoginView(APIView):
    authentication_classes = [BasicAuthentication]
    permission_classes = [AllowAny]

    def post(self, request):
        serializer = PoliceLoginSerializer(data=request.data)
        if serializer.is_valid():
            email = serializer.validated_data['email']
            password = serializer.validated_data['password']
            user = authenticate(email=email, password=password)

            if user and isinstance(user, User): 
                 if user.user_type == 'police':
                    refresh = RefreshToken.for_user(user)

                    user_details = {
                        "name": user.name,
                        "email": user.email,
                        "phone_number": user.phone_number,
                    }

                    return Response({
                        "message": "Login successful",
                        "user_detail": user_details ,
                        "access_token": str(refresh.access_token),
                        "refresh_token": str(refresh),
                    }, status=status.HTTP_200_OK)

            return Response({"message": "Invalid email or password"}, status=status.HTTP_401_UNAUTHORIZED)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



class CreateCriminalView(APIView):
    permission_classes = [IsAuthenticated]
    def post(self, request):
        user = request.user 
        if user.user_type == 'police':
            serializer = CriminalSerializer(data=request.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response({"message": "Police Can Only Add Criminals"}, status=status.HTTP_400_BAD_REQUEST)


# View to list all criminals
class ListCriminalsView(APIView):
    permission_classes = [IsAuthenticated]
    def get(self, request):
        criminals = Criminal.objects.all()
        serializer = CriminalSerializer(criminals, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)



class CreateFeedbackView(APIView):
    permission_classes = [IsAuthenticated]

    def post(self, request):
        serializer = FeedbackSerializer(data=request.data, context={'request': request})  # Pass context
        if serializer.is_valid():
            serializer.save()
            return Response({"message":"Thank You For Your FeedBack"}, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ListFeedbacksView(APIView):

    permission_classes = [IsAuthenticated]

    def get(self, request):
        user = request.user
        if user.user_type == 'police':
            user = request.user  # The authenticated user
            feedback = Feedback.objects.all()  # Filter posts by the current user
            serializer = FeedbackSerializer(feedback, many=True)  # Serialize the posts
            return Response(serializer.data, status=status.HTTP_200_OK)

        else:
            return Response({"message": "Only Police can view Feedback"}, status=status.HTTP_401_UNAUTHORIZED)



@method_decorator(csrf_exempt, name='dispatch')
class VideoUploadView(APIView):
    permission_classes = [IsAuthenticated]
    def post(self, request, *args, **kwargs):
        user = request.user
        if user.user_type == 'user':
            # Get the video file and location data from the request
            video_file = request.FILES.get('video_file')
            file_name = video_file.name
            location_longitude = request.POST.get('location_longitude')
            location_latitude = request.POST.get('location_latitude')


            # Validate that the video and location data are provided
            if not video_file or not location_longitude or not location_latitude:
                return JsonResponse({'error': 'Missing required fields'}, status=400)

            # Save the uploaded video file
            fs = FileSystemStorage(location=os.path.join(settings.MEDIA_ROOT,'videos'))
            video_path = fs.save(video_file.name , video_file)
            video_path = os.path.join(settings.MEDIA_ROOT, 'videos' , video_file.name)
            video_url = fs.url(video_path)

            def video_to_frames(video_path, interval_seconds):
                frames = []
                cap = cv2.VideoCapture(video_path)
                print(f"Attempting to open video file at: {os.path.abspath(video_path)}")
                if not cap.isOpened():
                    print("Error: Unable to open video file.")
                    return []

                fps = cap.get(cv2.CAP_PROP_FPS)  # Get FPS of the video
                video_duration = int(cap.get(cv2.CAP_PROP_FRAME_COUNT) / fps)

                for timestamp in range(0, video_duration, interval_seconds):
                    frame_position = int(timestamp * fps)
                    cap.set(cv2.CAP_PROP_POS_FRAMES, frame_position)
                    ret, frame = cap.read()

                    if ret:
                        frames.append((frame, timestamp)) 
                        print(f"Frame captured at {timestamp:.2f} seconds")
                    else:
                        print(f"Could not capture frame at {timestamp:.2f} seconds")

                cap.release()
                print(frames)
                return frames

            frame = video_to_frames(video_path=video_path , interval_seconds=1)

            # Process the video to detect faces
            known_faces_dir = os.path.join(settings.MEDIA_ROOT, 'criminals_image')
            output_folder = os.path.join(settings.MEDIA_ROOT, 'detected_frames')  # Define output folder for processed frames
            saved_frame_count, processed_folder = process_video(video_path, interval_seconds=10, output_folder=output_folder , known_faces_dir = known_faces_dir , frames = frame)

            # Store the video information in the database
            processed_video = ProcessedVideo(
                user = user,
                video_file=video_path,
                location_longitude=location_longitude,
                location_latitude=location_latitude,
                processed_frames_path=processed_folder
            )
            processed_video.save()

            # Return the response to the user
            return JsonResponse({
                'message': 'Video uploaded and processed successfully.',
                'processed_frame_count': saved_frame_count,
                'video_url': video_url,
                'processed_frames_path': processed_folder
            })
        else:
            return JsonResponse({'error': 'Not authorized to upload videos'}, status=403)


class ListVideosAPIView(APIView):
    permission_classes = [IsAuthenticated]

    def get(self, request):
        video_dir = os.path.join(settings.MEDIA_ROOT, 'videos')
        if os.path.exists(video_dir):
            videos = [
                video for video in os.listdir(video_dir)
                if os.path.isfile(os.path.join(video_dir, video))
            ]
            return Response({'videos': videos})
        return Response({'error': 'Directory not found'}, status=404)




class ListFramesAPIView(APIView):
    permission_classes = [IsAuthenticated]

    def get(self, request):
        # Get the path to the detected_frames folder
        frames_directory = os.path.join(settings.MEDIA_ROOT, 'detected_frames')

        # List all image files in the detected_frames folder
        frame_files = []
        if os.path.exists(frames_directory):
            for filename in os.listdir(frames_directory):
                if filename.endswith(('.jpg', '.jpeg', '.png')):  # Filter image files
                    frame_url = os.path.join('media', 'detected_frames', filename)  # Create URL
                    frame_files.append(frame_url)

        return Response({'frame_files': frame_files})


class AllVideosInfoView(APIView):
    permission_classes = [IsAuthenticated]

    def get(self, request, *args, **kwargs):
        user = request.user

        # Retrieve all videos uploaded by the user
        videos = ProcessedVideo.objects.filter(user=user).prefetch_related('frames')

        # Build the response
        videos_data = []
        for video in videos:

            # Add video metadata
            videos_data.append({
                
                'sender_name': video.user.name,
                'video_url': video.video_file.url,
                'location': {
                    'longitude': video.location_longitude,
                    'latitude': video.location_latitude,
                },
                'frames': [
                    {
                        'frame_url': frame.frame_file.url,
                        'timestamp': frame.timestamp
                    }
                    for frame in video.frames.all()
                ],
                'processed_frames_path': video.processed_frames_path,
            })

        return JsonResponse({'videos': videos_data}, safe=False, status=200)