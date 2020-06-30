package br.pro.hashi.ensino.desagil.aps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MuxGateTest {
    @Test
    public void whenReceivingFalseFalseFalseShouldReturnFalse() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterFalse());
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingFalseFalseTrueShouldReturnTrue() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingFalseTrueFalseShouldReturnFalse() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterFalse());
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingFalseTrueTrueShouldReturnFalse() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterTrue());
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingTrueFalseFalseShouldReturnFalse() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterFalse());
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingTrueFalseTrueShouldReturnTrue() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueTrueFalseShouldReturnTrue() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterFalse());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueTrueTrueShouldReturnTrue() {
        MuxGate gate = new MuxGate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }
}
