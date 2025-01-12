from django.urls import path
from .views import RegisterUserView , UserLoginView , PoliceLoginView , RegisterPoliceView


urlpatterns = [
    path('user/login/' , UserLoginView.as_view() , name='user-login'),
    path('user/register/' , RegisterUserView.as_view() , name='user-register'),
    path('police/login/' , PoliceLoginView.as_view() , name='police-login'),
    path('police/register/' , RegisterPoliceView.as_view() , name='police-register'),  

]
