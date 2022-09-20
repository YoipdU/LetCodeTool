package LetCodeTool;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过反射获取接口实现类的工具
 * 获取接口class文件路径，获取该路径下的所有文件，排除非.class结尾的文件，通过反射获取class文件的接口信息，并比较是否目标接口相符合。
 */
public class ClassUtil {

    /**
     * 获取接口的所有实现类
     * @param in
     * @return
     */
    public List<Object> getAllClassByInterface(Class in) {
        if(!in.isInterface()){
            try{
                throw new ClassNotFoundException("这不是一个接口");
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }

        List<Object> result = new ArrayList<Object>();
        String path = getClassPath(in);
        List<File> fileList = getClassFiles(path);
        try {
            List<Class> classList = getAllClass(fileList, path);
            result = filterClass(in, classList);
        } catch (ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
            System.out.println("异常建议：将LetCode的实现类的修饰符修改为public");
        }
        return result;
    }

    private String getClassPath(Class obj){
        return obj.getResource("/").getPath();
    }

    private List<File> getClassFiles(String classPath) {
        File classFile = new File(classPath);
        List<File> fileList = new ArrayList<File>();
        fileList = getFile(classFile, fileList);
        for (int i = 0; i < fileList.size(); i++) {
            if (!fileList.get(i).getName().endsWith(".class")) {
                fileList.remove(i);
                i--;
            }
        }
        return fileList;
    }

    private List<File> getFile(File file, List<File> result) {
        if (file == null) {
            return result;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFile(f, result);
            } else {
                result.add(f);
            }
        }
        return result;
    }

    private List<Class> getAllClass(List<File> fileList, String classPath) throws ClassNotFoundException {
        List<Class> classList = new ArrayList<Class>();
        String packageName = new String();
        if (classPath.charAt(0) == '/') {
            classPath = classPath.substring(1);
            classPath = classPath.replace('/','\\');
        }
        for (File f : fileList) {
            packageName = f.getPath().replace(classPath, "");
            packageName = packageName.replace("\\", ".");
            // 去除.class后缀
            packageName = packageName.substring(0, packageName.length() - 6);
            classList.add(Class.forName(packageName));
        }
        return classList;
    }

    private List<Object> filterClass(Class interfaceObj, List<Class> classList) throws InstantiationException, IllegalAccessException {
        List<Object> result = new ArrayList<Object>();
        for (Class clazz : classList) {
            if(clazz.isInterface()){
                continue;
            }
            Class[] classes = clazz.getInterfaces();
            for (Class c : classes) {
                if (interfaceObj.getName().equals(c.getName())) {
                    result.add(clazz.newInstance());
                }
            }
        }
        return result;
    }
}
