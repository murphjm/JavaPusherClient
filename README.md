# JavaPusherClient, a Java / Android Pusher Client

Web Site: [Public Static Droid Main](http://publicstaticdroidmain.com/)

[Pusher] (http://www.pusherapp.com) is a push notification service that uses [WebSockets] (http://en.wikipedia.org/wiki/WebSocket) for relaying messages back and forth between clients.  This allows real time messaging between a diverse range of applications running on Web browsers, Android devices and now any other place you use Java.

## Examples
### Creating a Pusher Event Listener
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
	Pusher pusher = new Pusher(YOUR_API_KEY);   
	pusher.setPusherListener(eventListener);
	pusher.connect();  
### Subscribing to Channels
	// Public Channel
	channel = pusher.subscribe(PUSHER_CHANNEL);  
	
	// Private Channel
	channel = pusher.subscribe(PUSHER_CHANNEL, AUTH_TOKEN);  
	
	// Presence Channel
	channel = pusher.subscribe(PUSHER_CHANNEL, AUTH_TOKEN, USER_ID);  	
### Triggering Channel Events
	channel.send("trigger-event", new JSONObject()); 
### Binding to Channel Events
	channel.bind("price-updated", new ChannelListener() {  
		@Override  
		public void onMessage(String message) {  
			System.out.println("Received bound channel message: " + message);  
		}  
	});  
## Credits
JavaPusherClient uses the [weberknecht] (https://github.com/rbaier/weberknecht) Java WebSockets library by Roderick Baier.