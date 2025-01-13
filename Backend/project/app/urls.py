from django.urls import path
from .views import RegisterUserView , UserLoginView , AllVideosInfoView ,PoliceLoginView , RegisterPoliceView , CreateCriminalView , ListCriminalsView , CreateFeedbackView , ListFeedbacksView , VideoUploadView , ListVideosAPIView , ListFramesAPIView


urlpatterns = [
    path('user/login/' , UserLoginView.as_view() , name='user-login'),
    path('user/register/' , RegisterUserView.as_view() , name='user-register'),
    path('police/login/' , PoliceLoginView.as_view() , name='police-login'),
    path('police/register/' , RegisterPoliceView.as_view() , name='police-register'),
    path('add/criminal/' , CreateCriminalView.as_view() , name='create-criminal'),
    path('list/criminal/' , ListCriminalsView.as_view() , name='list-criminal'),
    path('create/feedback/' , CreateFeedbackView.as_view() , name='feedback-create'),
    path('list/feedback/' , ListFeedbacksView.as_view() , name='feedback-list'),
    path('upload/', VideoUploadView.as_view(), name='video-upload'),
    path('videos/', ListVideosAPIView.as_view(), name='list-videos'),
    path('frames/' , ListFramesAPIView.as_view(), name='list-frame'),
    path('all/', AllVideosInfoView.as_view(), name='all-videos-info'),
]
