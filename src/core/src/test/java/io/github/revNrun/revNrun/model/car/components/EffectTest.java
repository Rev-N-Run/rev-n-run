package io.github.revNrun.revNrun.model.car.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffectTest {
     @Test
    public void testValidConstructorParameters() {
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

     @Test
    public void testLimitValueConstructor() {
         float maximumValue = 20;
         float minimumValue = -20;

         Effect gripEffect = new Effect(EffectType.GRIP, 50);
         Effect accelerationEffect = new Effect(EffectType.ACCELERATION, -43);
         Effect maxSpeedEffect = new Effect(EffectType.MAX_SPEED, 27);

         assertEquals(EffectType.GRIP, gripEffect.getEffect());
         assertEquals(EffectType.ACCELERATION, accelerationEffect.getEffect());
         assertEquals(EffectType.MAX_SPEED, maxSpeedEffect.getEffect());

         // Should limit the values in the range [-20,20]. Example, if value -50
         // is set, should assign -20, if value 50, then should assign 20.
         assertEquals(maximumValue, gripEffect.getValue());
         assertEquals(minimumValue, accelerationEffect.getValue());
         assertEquals(maximumValue, maxSpeedEffect.getValue());

         // Frontier values
         gripEffect = new Effect(EffectType.GRIP, 20);
         accelerationEffect = new Effect(EffectType.ACCELERATION, -20);

         assertEquals(maximumValue, gripEffect.getValue());
         assertEquals(minimumValue, accelerationEffect.getValue());

         // Internal frontier values
         gripEffect = new Effect(EffectType.GRIP, 19.9f);
         accelerationEffect = new Effect(EffectType.ACCELERATION, -19.9f);

         assertEquals(19.9f, gripEffect.getValue());
         assertEquals(-19.9f, accelerationEffect.getValue());

         // External frontier values
         gripEffect = new Effect(EffectType.GRIP, 20.1f);
         accelerationEffect = new Effect(EffectType.ACCELERATION, -20.1f);

         assertEquals(20f, gripEffect.getValue());
         assertEquals(-20f, accelerationEffect.getValue());

         // Internal values
         gripEffect = new Effect(EffectType.GRIP, 0);
         accelerationEffect = new Effect(EffectType.ACCELERATION, 3);
         maxSpeedEffect = new Effect(EffectType.MAX_SPEED, -2);

         assertEquals(0, gripEffect.getValue());
         assertEquals(3, accelerationEffect.getValue());
         assertEquals(-2, maxSpeedEffect.getValue());
    }

    @Test
    public void testBrakeEffect() {
        Effect brakeEffect = new Effect(EffectType.BRAKE, 5);
        assertEquals(EffectType.BRAKE, brakeEffect.getEffect());
        assertEquals(5, brakeEffect.getValue());

        brakeEffect = new Effect(EffectType.BRAKE, -4);
        assertEquals(EffectType.BRAKE, brakeEffect.getEffect());
        assertEquals(-4, brakeEffect.getValue());

        brakeEffect = new Effect(EffectType.BRAKE, 0);
        assertEquals(EffectType.BRAKE, brakeEffect.getEffect());
        assertEquals(0, brakeEffect.getValue());

        // Frontier values
        brakeEffect = new Effect(EffectType.BRAKE, 20);
        assertEquals(20, brakeEffect.getValue());
        brakeEffect = new Effect(EffectType.BRAKE, -20);
        assertEquals(-20, brakeEffect.getValue());

        // Internal frontier values
        brakeEffect = new Effect(EffectType.BRAKE, 19.9f);
        assertEquals(19.9f, brakeEffect.getValue());
        brakeEffect = new Effect(EffectType.BRAKE, -19.9f);
        assertEquals(-19.9f, brakeEffect.getValue());

        // External frontier values
        brakeEffect = new Effect(EffectType.BRAKE, 20.1f);
        assertEquals(20f, brakeEffect.getValue());
        brakeEffect = new Effect(EffectType.BRAKE, -20.1f);
        assertEquals(-20f, brakeEffect.getValue());

    }

}
