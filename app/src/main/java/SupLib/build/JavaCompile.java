package SupLib.build;

//import com.besome.sketch.define.DefineErroCode;
//import com.besome.sketch.exp.SketchBuildException;
//import com.besome.sketch.exp.SketchCompileException;
import SupLib.EXTools.Workspace;
import SupLib.eclipse.eclipse.jdt.Main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class JavaCompile {
    StringBuffer sb = new StringBuffer();

    class ErrorOutputString extends OutputStream {
        ErrorOutputString() {
        }

        public void write(int oneByte) throws IOException {
            JavaCompile.this.sb.append((char) oneByte);
        }
    }

    public void compile(Workspace workspace) {
        Main javac = new Main(new PrintWriter(System.out), new PrintWriter(new ErrorOutputString()), false, null, null);
        javac.compile(new String[]{"-1.6", "-nowarn", "-d", workspace.classesDir, "-classpath", workspace.androidJar + ":" + workspace.genDir, "-proc:none", "-sourcepath", workspace.srcDir, workspace.mainJava, workspace.applicationJava});
        if (javac.globalErrorsCount <= 0) {
            return;
        }
//        if (this.sb.toString().contains("Unreachable code")) {
//            throw new SketchCompileException(DefineErroCode.ERROR_UN_REACHABLE);
//        } else if (this.sb.toString().contains("break cannot be used outside ")) {
//            throw new SketchCompileException(DefineErroCode.ERROR_BREAK_OUTSIDE);
//        } else {
//            throw new SketchBuildException(this.sb.toString());
//        }
    }
}
