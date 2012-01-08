# JavaPusherClient, a Java / Android Pusher Client

Web Site: [Public Static Droid Main](http://publicstaticdroidmain.com/)

[Pusher] (http://www.pusherapp.com) is a Push Notification service that uses Websockets for relaying messages back and forth between clients.  This allows real time messaging between a diverse range of applications running on Web browsers, mobile devices and now Arduinos.  It is my hope that allowing devices to easily send information about themselves as well as respond to messages received from applications and other devices will result in some interesting applications.

## Examples
	PusherListener eventListener = new PusherListener() {  
		Channel channel;
		
		@Override
		public void onConnect(String socketId) {
			System.out.println("Pusher connected. Socket Id is: " + socketId);
			channel = pusher.subscribe(PUSHER_CHANNEL);
			System.out.println("Subscribed to channel: " + channel);
			channel.send("client-test", new JSONObject());
			
			channel.bind("price-updated", new ChannelListener() {
				@Override
				public void onMessage(String message) {
					System.out.println("Received bound channel message: " + message);
				}
			});
		}
	
		@Override
		public void onMessage(String message) {
			System.out.println("Received message from Pusher: " + message);
		}
	
		@Override
		public void onDisconnect() {
			System.out.println("Pusher disconnected.");
		}
	};
### Connecting to Pusher
	Pusher pusher = new Pusher(YOUR_API_KEY, PusherListener);   
	pusher.connect();  
### Channels
	channel = pusher.subscribe(PUSHER_CHANNEL);  
	 
### Triggering Events
	channel.send("trigger-event", new JSONObject()); 
### Binding to Events
	channel.bind("price-updated", new ChannelListener() {  
		@Override  
		public void onMessage(String message) {  
			System.out.println("Received bound channel message: " + message);  
		}  
	});  
## Credits
JavaPusherClient uses the [weberknecht] (https://github.com/rbaier/weberknecht) Java WebSockets library by Roderick Baier.