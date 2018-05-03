package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //濞夈劏袙閻ㄥ嫮娲伴弽锟�
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public String field(); //鐎涙顔岄崥宥囆�
	public boolean primaryKey() default false; //閺勵垰鎯佹稉杞板瘜闁匡拷
	public String type() default "VACHAR(100)"; //鐎涙顔岀猾璇茬��
	public boolean defaultNull() default true; //閺勵垰鎯侀崗浣筋啅娑撹櫣鈹�
	public String comment() default "";//濞夈劑鍣�
}
