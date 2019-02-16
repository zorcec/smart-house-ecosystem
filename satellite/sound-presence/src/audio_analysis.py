import sys
sys.path.insert(0, "./lib/pyAudioAnalysis/pyAudioAnalysis")

import os, alsaaudio, time, audioop, numpy, glob,  scipy, subprocess, wave, cPickle, threading, shutil
import matplotlib.pyplot as plt
import scipy.io.wavfile as wavfile
from scipy.fftpack import rfft
import audioFeatureExtraction as aF	
import audioTrainTest as aT
import audioSegmentation as aS
from scipy.fftpack import fft

class AudioAnalysis():

	def initialize(self):
		self.midTermBufferSizeSec = 1
		self.duration = 1
		self.Fs = 8000
		self.outputDirectory = "../temp"

		self.inp = alsaaudio.PCM(alsaaudio.PCM_CAPTURE, alsaaudio.PCM_NONBLOCK)

		self.inp.setchannels(1)
		self.inp.setrate(self.Fs)
		self.inp.setformat(alsaaudio.PCM_FORMAT_S16_LE)
		self.inp.setperiodsize(512)

		while True:
			data = self.capture()
			self.analyzePwm(data)
			#self.saveToFile(data)


	def analyzePwm(self, data):
		pwm = 0
		values, features = aF.stFeatureExtraction(data, self.Fs, 0.1 * self.Fs, 0.05 * self.Fs)
		for index, feature in enumerate(features):
			if feature == "energy":
				maxValue = numpy.amax(values[index])
				if maxValue > pwm:
					pwm = maxValue
		print "pwm: %s" % pwm


	def saveToFile(self, data):
		filePath = os.path.abspath("%s/%s.wav" % (self.outputDirectory, time.time()))
		wavfile.write(filePath, self.Fs, numpy.int16(data))


	def capture(self):
		midTermBufferSize = int(self.midTermBufferSizeSec * self.Fs)
		allData = []
		midTermBuffer = []
		curWindow = []

		while len(allData) < self.duration * self.Fs:
			time.sleep(0.1)
			l, data = self.inp.read()
			if l:
				for i in range(l):
					curWindow.append(audioop.getsample(data, 2, i))		
				if (len(curWindow) + len(midTermBuffer) > midTermBufferSize):
					samplesToCopyToMidBuffer = midTermBufferSize - len(midTermBuffer)
				else:
					samplesToCopyToMidBuffer = len(curWindow)
				midTermBuffer = midTermBuffer + curWindow[0:samplesToCopyToMidBuffer];
				del(curWindow[0:samplesToCopyToMidBuffer])

			if len(midTermBuffer) == midTermBufferSize:
				allData = allData + midTermBuffer
				midTermBuffer = []

		return allData


if __name__ == '__main__':
	audioAnalysis = AudioAnalysis()
	audioAnalysis.initialize()
