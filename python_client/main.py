import asyncio
import websockets

WEBSOCKET_URL = "ws://localhost:8080/ws/websocket"

USER_ID = 1

async def listen_to_notifications():
    async with websockets.connect(WEBSOCKET_URL) as websocket:
        print("Connected to WebSocket server")

        connect_frame = (
            "CONNECT\n"
            "accept-version:1.2\n"
            "host:localhost\n"
            "\n"
            "\x00"
        )
        await websocket.send(connect_frame)
        response = await websocket.recv()
        print(f"STOMP Connection Response: {response}")

        # Step 2: Subscribe to notifications
        subscribe_frame = (
            f"SUBSCRIBE\n"
            f"id:sub-{USER_ID}\n"
            f"destination:/topic/notifications/{USER_ID}\n"
            "\n"
            "\x00"
        )
        await websocket.send(subscribe_frame)
        print(f"Subscribed to user {USER_ID} notifications")

        while True:
            message = await websocket.recv()
            print(f"Received notification: {message}")

asyncio.get_event_loop().run_until_complete(listen_to_notifications())