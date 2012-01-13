package com.justinschultz.examples;

/*	
 *  Copyright (C) 2012 Justin Schultz
 *  JavaPusherClient, a Pusher (http://pusherapp.com) client for Java
 *  
 *  http://justinschultz.com/
 *  http://publicstaticdroidmain.com/
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

import org.json.JSONObject;

import com.justinschultz.pusherclient.ChannelListener;
import com.justinschultz.pusherclient.Pusher;
import com.justinschultz.pusherclient.PusherListener;
import com.justinschultz.pusherclient.Pusher.Channel;

public class PusherTest {
	private static final String PUSHER_API_KEY = "80bbbe17a2e65338705a";
	private static final String PUSHER_CHANNEL = "test-channel";
	private static Pusher pusher;
	
	public static void main(String[] args) {	
		PusherListener eventListener = new PusherListener() {
			Channel channel;
			
			@Override
			public void onConnect(String socketId) {
				System.out.println("Pusher connected. Socket Id is: " + socketId);
				channel = pusher.subscribe(PUSHER_CHANNEL);
				System.out.println("Subscribed to channel: " + channel);
				channel.send("client-event-test", new JSONObject());
				
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
		
		pusher = new Pusher(PUSHER_API_KEY);
		pusher.setPusherListener(eventListener);
		pusher.connect();
	}
}
