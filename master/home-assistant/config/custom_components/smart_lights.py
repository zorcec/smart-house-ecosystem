import time
import threading
import logging

from homeassistant.helpers.event import track_state_change

# The domain of your component. Should be equal to the name of your component.
DOMAIN = 'smart_lights'
SERVICE_NAME = 'presence'
DEVICE_NAME = 'development'

# in seconds
LONG_TIMER = 15.0
SHORT_TIMER = 10.0

"""
NEXT
- configure automatization to use this service
- pass in DEVICE_NAME, optional, timers time + move sensitivity logic here?
"""

def setup(hass, config):

    def handle_register():
        logging.getLogger(__name__).info("Registered")

        obj = SmartLightsPresence()
        obj.initialize(hass, config)

    hass.services.register(DOMAIN, SERVICE_NAME, handle_register)

    # Return boolean to indicate that initialization was successfully.
    return True


class SmartLightsPresence():

    def initialize(self, hass, config):
        self.logger = logging.getLogger(__name__)
        self.hass = hass
        self.config = config
        self.logger.info("Initializing")
        self.last_motion_time = time.time()
        self.last_sound_presence = time.time()
        self.presence_time = time.time()

        track_state_change(hass, "sensor.%s_sensorio_motion" % DEVICE_NAME, self.motion)
        track_state_change(hass, "sensor.%s_sound_presence" % DEVICE_NAME, self.sound_presence)

        self.start_timers()

        self.logger.info("Smart lights initialized")


    def motion(self, entity_id, old, new):
        self.logger.info("Motion detected, time difference: %ss" % self.time_difference(self.last_motion_time))
        self.last_motion_time = time.time()
        self.presence_time = time.time()
        self.restart_timers()


    def sound_presence(self, entity_id, old, new):
        self.logger.info("Sound presence detected, time difference: %ss" % self.time_difference(self.last_sound_presence))
        self.last_sound_presence = time.time()
        self.presence_time = time.time()
        self.restart_timers()


    def time_difference(self, sinceTime):
        return time.time() - sinceTime


    def short_presence(self):
        self.logger.info("Short presence is over")
        self.short_presence_timer = None


    def long_presence(self):
        self.logger.info("Long presence is over")
        self.long_presence_timer = None


    def restart_timers(self):
        self.logger.info("Restarting presence timers")

        if self.short_presence_timer is not None:
            self.short_presence_timer.cancel()
            self.short_presence_timer = None

        if self.long_presence_timer is not None:
            self.long_presence_timer.cancel()
            self.long_presence_timer = None

        self.start_timers()


    def start_timers(self):
        self.short_presence_timer = threading.Timer(SHORT_TIMER, self.short_presence)
        self.short_presence_timer.start()

        self.long_presence_timer = threading.Timer(LONG_TIMER, self.long_presence)
        self.long_presence_timer.start()