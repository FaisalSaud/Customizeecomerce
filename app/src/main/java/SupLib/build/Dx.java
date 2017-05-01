package SupLib.build;

import SupLib.Dx.command.Main;
import SupLib.EXTools.Workspace;

public class Dx {
    public void dx(Workspace workspace) throws Exception {
        Main.main(new String[]{"--dex", "--verbose", "--output=" + workspace.outDex, workspace.classesDir});
    }
}
