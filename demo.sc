// create livecode server in Interface.Server app.
// Interface.Server must be running before executing this line.
Interface.init;

// after intitializing the livecode server you should connect to
// it from a browser. By default you can do this at the IP address:
// http://your.computer.address:8080

// create a slider, by default it will fill the entire interface
c = Interface.slider;

// read the value of the slider
c.value;

// set the value of the slider.
c.value = 1;

// use a different device connected to Interface.Server for
// creating interfaces as identified by the client id #
// don't run this line if you only have one device connected to
// the Interface.Server application
Interface.use(1);

// create a button and pass a label, fill color and max value
d = Interface.button( (label:"TEST", fill:"#ff0", max:100) );

// read the button
d.value;

// create a simple synthdef to control
x = SynthDef("help-synth", {| freq = 440, vol = 1 |
    Out.ar(0, SinOsc.ar(freq) * vol);
}).play;

// create a slider and assign it to control the volume of
// our synth def
e = Interface.slider( \vol,  x);

// create a slider for control and specifiy output range
f = Interface.slider( \freq, x, (min:440, max:880) );

// remove widgets from interface
d.remove;

// execute arbitrary javascript on the device in current use
// in this instance we change the background color for all widgets
Interface.oscout.sendMsg("/interface/runScript", "panel.childBackground=\"#900\"")

// redo layout after removing widgets
Interface.oscout.sendMsg("/interface/runScript", "Interface.autogui.redoLayout()")

// clear entire interface
Interface.oscout.sendMsg("/interface/clear")