from django.conf import settings
from django.contrib.auth import get_user_model
from django.db import models
from django.core.files import File
from django.contrib.auth.models import AbstractBaseUser, Group ,PermissionsMixin, BaseUserManager, UserManager

# Create your models here.
class CustomUserManager(BaseUserManager):
    def create_user(self, email, name, phone_number, password=None, **extra_fields):
        if not email:
            raise ValueError('The Email field must be set')
        email = self.normalize_email(email)
        user = self.model(email=email, name=name, phone_number=phone_number, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, name, phone_number, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)

        if extra_fields.get('is_staff') is not True:
            raise ValueError('Superuser must have is_staff=True.')
        if extra_fields.get('is_superuser') is not True:
            raise ValueError('Superuser must have is_superuser=True.')

        # Create a superuser without requiring DOB
        return self.create_user(email=email, name=name, phone_number=phone_number, password=password, **extra_fields)


# Abstract base user
class BaseUser(AbstractBaseUser, PermissionsMixin):
    email = models.EmailField(unique=True)
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)

    objects = CustomUserManager()

    USERNAME_FIELD = 'email'

    class Meta:
        abstract = True


# User model
class User(AbstractBaseUser, PermissionsMixin):
    USER_TYPES = (
        ('user', 'User'),
        ('police', 'police'),
    )

    name = models.CharField(max_length=255)
    phone_number = models.CharField(max_length=20, unique=True)
    user_permissions = models.ManyToManyField('auth.Permission', related_name='user_permissions_set', blank=True)
    user_type = models.CharField(max_length=20, choices=USER_TYPES , default='user')

    # Define additional fields
    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    email = models.EmailField(unique=True)

    # Set custom manager
    objects = CustomUserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['name', 'phone_number']

    def __str__(self):
        return self.name


class Feedback(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE ,  related_name='user_feedback')
    title = models.CharField(max_length=20)
    content = models.TextField()
    created_at = models.DateField(auto_now_add=True)

    def __str__(self):
        return self.content