package io.github.revNrun.revNrun.model.car.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffectTest {
     @Test
    public void testConstructor() {
         // Testing for grip
         Effect gripEffect = new Effect(EffectType.GRIP, 5);
         Effect accelerationEffect = new Effect(EffectType.ACCELERATION, 3);
         Effect maxSpeedEffect = new Effect(EffectType.MAX_SPEED, -4);

         assertEquals(EffectType.GRIP, gripEffect.getEffect());
         assertEquals(EffectType.ACCELERATION, accelerationEffect.getEffect());
         assertEquals(EffectType.MAX_SPEED, maxSpeedEffect.getEffect());

         assertEquals(5, gripEffect.getValue());
         assertEquals(3, accelerationEffect.getValue());
         assertEquals(-4, maxSpeedEffect.getValue());
     }
}
