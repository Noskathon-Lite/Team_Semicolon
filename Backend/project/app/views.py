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
from .models import User , Feedback
from rest_framework.permissions import IsAuthenticated
import os
from django.http import JsonResponse
from django.views import View
import json
from django.core.files.storage import FileSystemStorage
from datetime import date , timedelta
from .serializers import UserSerializer , UserLoginSerializer , PoliceSerializer ,PoliceLoginSerializer , FeedbackSerializer
from rest_framework.authentication import BasicAuthentication
from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator



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


class CreateFeedbackView(APIView):
    permission_classes = [IsAuthenticated]

    def post(self, request):
        user = request.user  # The authenticated user
        data = request.data.copy()  # Make a mutable copy of the request data
        #data['user'] = 1  # Set the current user's ID in the data

        # Validate and save the post using the serializer
        serializer = FeedbackSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ListFeedbacksView(APIView):

    permission_classes = [IsAuthenticated]

    def get(self, request):
        user = request.user  # The authenticated user
        feedback = Feedback.objects.all()  # Filter posts by the current user
        serializer = FeedbackSerializer(feedback, many=True)  # Serialize the posts
        return Response(serializer.data, status=status.HTTP_200_OK)