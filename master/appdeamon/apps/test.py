import appdaemon.plugins.hass.hassapi as hass

class Test(hass.Hass):
  def initialize(self):
    self.log("Initialized")
