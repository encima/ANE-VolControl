package com.encima.volume
{
	import flash.events.EventDispatcher;

	public class VolumeController extends EventDispatcher
	{
		//----------------------------------------
		//
		// Variables
		//
		//----------------------------------------
		
		private static var _instance:VolumeController;
		
		//----------------------------------------
		//
		// Properties
		//
		//----------------------------------------
		
		private var _systemVolume:Number = 1;
		
		public function get systemVolume():Number {
			return _systemVolume;
		}
		
		public function set systemVolume( value:Number ):void {
			if ( _systemVolume == value ) {
				return;
			}
			
			_systemVolume = value;
		}
		
		//----------------------------------------
		//
		// Public Methods
		//
		//----------------------------------------
		
		public static function get instance():VolumeController {
			if ( !_instance ) {
				_instance = new VolumeController( new SingletonEnforcer() );
				_instance.init();
			}
			
			return _instance;
		}
		
		/**
		 * Changes the device's system volume.
		 *  
		 * @param newVolume The new system volume.  This value should be between 0 and 1.
		 */		
		public function setVolume( newVolume:Number ):void {
			if ( isNaN(newVolume) )  {
				newVolume = 1;
			}
			
			if ( newVolume < 0 ) {
				newVolume = 0;
			}
			
			if ( newVolume > 1 ) {
				newVolume = 1;
			}
			
			systemVolume = newVolume;
		}
		
		/**
		 * Cleans up the instance of the native extension. 
		 */		
		public function dispose():void { 
			
		}
		
		//----------------------------------------
		//
		// Handlers
		//
		//----------------------------------------
		
		private function init():void {
			
		}
		
		//----------------------------------------
		//
		// Constructor
		//
		//----------------------------------------
		
		/**
		 * Constructor. 
		 */		
		public function VolumeController( enforcer:SingletonEnforcer ) {
			super();
		}
	}
}

class SingletonEnforcer {
	
}