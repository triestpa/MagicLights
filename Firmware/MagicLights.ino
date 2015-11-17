// This #include statement was automatically added by the Particle IDE.
#include "neopixel/neopixel.h"

#define PIN    D4
#define N_LEDS 60
#define TYPE WS2812B

Adafruit_NeoPixel strip = Adafruit_NeoPixel(N_LEDS, PIN, TYPE);

uint32_t RED[3] = {strip.Color(170,57,57), strip.Color(128,21,21), strip.Color(85,0,0)};
uint32_t TAN[3] = {strip.Color(170,106,57), strip.Color(128,67,21), strip.Color(85,37,0)};
uint32_t BLUE[3] = {strip.Color(35,104,88), strip.Color(13,78,74), strip.Color(0,52,48)};
uint32_t GREEN[3] = {strip.Color(45,136,45), strip.Color(17,102,17), strip.Color(0,68,0)};


int setColor(String hexColor) {
    /*
    int hexValue = hexColor.toInt();
    int red = ((hexValue >> 16) & 0xFF) / 255.0;
    int green = ((hexValue >> 8) & 0xFF) / 255.0;
    int blue = ((hexValue >> 0xFF) & 0xFF) / 255.0;
    */
    int red = hexColor.substring(0,2).toInt();
    int green = hexColor.substring(3,5).toInt();
    int blue = hexColor.substring(6,8).toInt();

    uint32_t newColor = strip.Color(red,green,blue);

    for (uint16_t i=0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, newColor);
    strip.show();
    delay(25);
  }
}


void setup() {
    Particle.function("color",setColor);
    strip.begin();
    setColor("170057057");
}

void loop() {
    delay(50);
}


static void distributeColor(uint32_t colors[5]) {
  for (uint16_t i=0; i < strip.numPixels(); i++) {
    strip.setPixelColor(i, colors[i % 3]);
    strip.show();
    delay(25);
  }
}

static void chase(uint32_t c) {
  for(uint16_t i=0; i<strip.numPixels()+4; i++) {
      strip.setPixelColor(i  , c); // Draw new pixel
      strip.setPixelColor(i-4, 0); // Erase pixel a few steps back
      strip.show();
      delay(25);
  }
}