package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AnalogInputBlock extends TranslatorBlock
{
	public static final String ARDUBLOCK_ANALOG_READ_DEFINE = "int __ardublockAnalogRead(int pinNumber)\n{\npinMode(pinNumber, INPUT_PULLUP);\ndigitalWrite(pinNumber, HIGH);\nreturn analogRead(pinNumber);\n}\n\n";
	public AnalogInputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{

		translator.addDefinitionCommand(ARDUBLOCK_ANALOG_READ_DEFINE);
		String ret = "__ardublockAnalogRead(";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;

		/*
		String ret = "analogRead(";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
		*/
	}
}
