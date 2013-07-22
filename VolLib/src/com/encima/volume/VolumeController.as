package com.encima.volume
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;

	public class VolumeController extends EventDispatcher
	{
		private var extContext:ExtensionContext;
		private static var _instance:VolumeController;
		
		private var _sysVolume:Number = NaN;
		
		public function get sysVolume():Number {
			return _sysVolume;
		}
		
		public function set systemVolume( value:Number ):void {
			if ( _sysVolume == value ) {
				return;
			}
			
			_sysVolume = value;
		}
		
		public static function get instance():VolumeController {
			if(!_instance) {
				_instance = new VolumeController(new SingletonEnforcer());
				_instance.init();
			}
			return _instance;
		}
		
		private function setVolume(newVol:Number):void {
			if(isNaN(newVol)) {
				newVol = 1;
			}
			if(newVol < 0) {
				newVol = 0;
			}else if(newVol > 1) {
				newVol = 1;
			}
			
			extContext.call("setVolume", newVol);
			systemVolume = newVol;
		}
		
		private function init():void {
			extContext.call("init");
		}
		
		public function dispose():void {
			extContext.call("init");
		}
		
		private function onStatus( event:StatusEvent ):void {
			systemVolume = Number(event.level);
//			dispatchEvent(new VolumeEvent(VolumeEvent.VOLUME_CHANGED, systemVolume, false, false);
		}
		
		
		public function VolumeController(enforcer:SingletonEnforcer)
		{
			super();
			extContext = ExtensionContext.createExtensionContext("com.encima.volume", "");
			if(!extContext) {
				throw new Error("Volme ANE is not supported on this device");
			}
			extContext.addEventListener(StatusEvent.STATUS, onStatus);
		}
	}
}

class SingletonEnforcer {
	
}