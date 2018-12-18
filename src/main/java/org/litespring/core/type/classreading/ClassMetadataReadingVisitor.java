package org.litespring.core.type.classreading;

import org.litespring.util.ClassUtils;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.SpringAsmInfo;

public class ClassMetadataReadingVisitor extends ClassVisitor{

	private String className;

	private boolean isInterface;

	private boolean isAbstract;

	private boolean isFinal;

	private String superClassName;

	private String[] interfaces;
	
	public ClassMetadataReadingVisitor(){
		super(SpringAsmInfo.ASM_VERSION);
	}
	
	public void visit(int version,int access,String name,String signature,String supername,String [] interfaces){
		this.className = ClassUtils.convertResourcePathToClassName(name);
		this.isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
		this.isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
		this.isFinal = ((access & Opcodes.ACC_FINAL) != 0 );
		
		if(supername != null){
			this.superClassName = ClassUtils.convertResourcePathToClassName(supername);
		}
		
		this.interfaces = new String[interfaces.length];
		
		for(int i = 0; i < interfaces.length; i++){
			this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
		}
		
	}

	public String getClassName() {
		return className;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public String[] getInterfaceNames() {
		return this.interfaces;
	}
    
	



}