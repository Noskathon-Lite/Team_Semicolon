import json
from channels.generic.websocket import AsyncWebsocketConsumer
from channels.db import database_sync_to_async

class AlertConsumer(AsyncWebsocketConsumer):
    async def connect(self):
        user = self.scope["user"]  # Get the authenticated user
        if user.is_authenticated and await self.is_police(user):
            self.group_name = "police_alerts"
            await self.channel_layer.group_add(
                self.group_name,
                self.channel_name
            )
            await self.accept()
        else:
            # Reject the connection if not police or not authenticated
            await self.close()

    async def disconnect(self, close_code):
        if hasattr(self, "group_name"):
            await self.channel_layer.group_discard(
                self.group_name,
                self.channel_name
            )

    async def receive(self, text_data):
        data = json.loads(text_data)
        message = data['message']

        # Broadcast the message to the group
        await self.channel_layer.group_send(
            self.group_name,
            {
                'type': 'alert_message',
                'message': message
            }
        )

    async def alert_message(self, event):
        message = event['message']

        # Send message to WebSocket
        await self.send(text_data=json.dumps({
            'message': message
        }))

    @database_sync_to_async
    def is_police(self, user):
        return user.user_type == "police"
