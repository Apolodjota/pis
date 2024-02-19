/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
//cambios en Docente y Estudiante, veamos si sube
/**
 *
 * @author sakotaz
 */
public class Utilidades {
    public static Field getField(Class clazz, String attribute){
        Field[] fields = clazz.getFields();
        Field resp = null;
        for(Field f : fields){
            if (attribute.equalsIgnoreCase(f.getName())){
                resp = f;
            }
        }
        Field [] fields1 = clazz.getDeclaredFields();
        for(Field f : fields1){
            if (attribute.equalsIgnoreCase(f.getName())){
            resp = f;
            }
        }        
        return resp;
    }
    
    public static Object getData(Object clas, String attribute) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class clazz = clas.getClass();
        Field f = getField(clazz, attribute);
        Object obj = null;
        if (f != null) {
            String auxAttribute = "get"+capitalizar(attribute);
            Method method = null;
            for(Method m : clazz.getMethods()) {
                //System.out.println(m.getName());
                if (m.getName().equalsIgnoreCase(auxAttribute)){
                    method = m;
                    break;
                }
            }
        
            for(Method m : clazz.getDeclaredMethods()) {
                //System.out.println(m.getName());
                if (m.getName().equalsIgnoreCase(auxAttribute)){
                    method = m;
                    break;
                }
            }
            if (method != null){
                obj = method.invoke(clas);
            }
        }
        return obj;
    }
    
    public static String capitalizar(String cnd) {
        char [] caracteres = cnd.toCharArray();
        String aux = String.valueOf(caracteres[0]).toUpperCase();
        caracteres[0] = aux.charAt(0);
        return new String(caracteres);
    }
    
    public static String calcularDiferencia(Date now, Date date){
        String dif = "";
        try {
            /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date now = df.parse("2024-02-20 23:00:00");
            java.util.Date date = df.parse("2024-02-19 00:51:50");*/
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            //System.out.println("" + day + " días, " + hour + " horas, " + min + " minutos, " + s + " segundos");
            if(day>0){
                dif = day+" día/s "+hour+" hora/s";
            }else if(hour>0){
                dif = hour+" hora/s "+min+" minuto/s";
            }else if(min > 0){
                dif = min+" minuto/s "+s+" segundo/s";
            }else{
                dif = s+" segundo/s";
            }
        } catch (Exception e) {
        }
        return dif;
    }
}
