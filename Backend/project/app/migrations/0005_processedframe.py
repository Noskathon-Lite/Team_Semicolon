# Generated by Django 5.1.4 on 2025-01-13 05:17

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ("app", "0004_processedvideo"),
    ]

    operations = [
        migrations.CreateModel(
            name="ProcessedFrame",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("frame_file", models.ImageField(upload_to="detected_frames/")),
                ("timestamp", models.FloatField()),
                ("created_at", models.DateTimeField(auto_now_add=True)),
                (
                    "video",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        related_name="frames",
                        to="app.processedvideo",
                    ),
                ),
            ],
        ),
    ]