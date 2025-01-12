from rest_framework import serializers
from .models import User , Criminal , Feedback
from django.utils import timezone
from datetime import date , timedelta
from django.contrib.auth import get_user_model
from django.shortcuts import get_object_or_404



class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'email', 'name', 'phone_number', 'password']
        extra_kwargs = {
            'password': {'write_only': True}
             
        }


    def create(self, validated_data):
        password = validated_data.pop('password', None)
        validated_data['user_type'] = 'user'
        user = User.objects.create(**validated_data)
        if password:
            user.set_password(password)  # Hash the password
            user.save()
        return user

class UserLoginSerializer(serializers.Serializer):
    phone_number = serializers.CharField()
    password = serializers.CharField(write_only=True)



class PoliceSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'email', 'name', 'phone_number', 'password']
        extra_kwargs = {
            'password': {'write_only': True}
             
        }


    def create(self, validated_data):
        password = validated_data.pop('password', None)
        validated_data['user_type'] = 'police'
        user = User.objects.create(**validated_data)
        if password:
            user.set_password(password)  # Hash the password
            user.save()
        return user


class PoliceLoginSerializer(serializers.Serializer):
    email = serializers.EmailField()
    password = serializers.CharField(write_only=True)


class CriminalSerializer(serializers.ModelSerializer):
    class Meta:
        model = Criminal
        fields = ['name' , 'age' , 'gender' ,  'case' , 'image']

        def validate_user(self, user):
            if user.user_type != 'police':
                raise serializers.ValidationError("Criminal must be assigned to a police officer.")
            return user



class FeedbackSerializer(serializers.ModelSerializer):
    user_name = serializers.CharField(source='user.name', read_only=True)
    class Meta:
        model = Feedback
        fields = ['user_name', 'content', 'created_at' , 'title']
        read_only_fields = ['id', 'created_at']

    def create(self, validated_data):
        request = self.context.get('request')
        if request and request.user.is_authenticated:
            validated_data['user'] = request.user
        else:
            raise serializers.ValidationError({"user": "Authentication is required to create feedback."})
        return super().create(validated_data)




