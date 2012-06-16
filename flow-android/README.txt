
commands for emulating an sdcar a putting files on it
--------------------------------------------------------------

mksdcard 100M sdcard.iso
adb push "dummy.jpg" /sdcard/
start emulator with sdcard: -sdcard "<path>\sdcard.iso"


set geo location
---------------------------
http://developer.android.com/guide/developing/tools/emulator.html#console
This requires that and emulator is already running

in cmd: telnet localhost <port> 
example: telnet localhost 5554 (this will connect to the first running emulator)
port numbers: 5554, 5556, 5557, 5558 .... (5555 is used by adb)


enter command: geo fix -121.45356 46.51119 4392





Debug on device
----------------------------------
Enable "USB Debugging" on the device: Settings->Applications->Development

Connect with cmd:
adb -s 0403888D1801600A shell
logcat




