/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.SupLib.eclipse/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package SupLib.Dx.ssa.back;

import SupLib.Dx.ssa.SsaMethod;
import SupLib.Dx.ssa.SsaBasicBlock;
import SupLib.Dx.ssa.SsaInsn;
import SupLib.Dx.ssa.PhiInsn;
import SupLib.Dx.ssa.SetFactory;
import SupLib.Dx.rop.code.RegisterSpec;
import SupLib.Dx.util.IntSet;
import SupLib.Dx.util.BitIntSet;
import SupLib.Dx.util.ListIntSet;

import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;

/**
 * A register interference graph
 */
public class InterferenceGraph {
    /**
     * {@code non-null;} interference graph, indexed by register in
     * both dimensions
     */
    private final ArrayList<IntSet> interference;

    /**
     * Creates a new graph.
     *
     * @param countRegs {@code >= 0;} the start count of registers in
     * the namespace. New registers can be added subsequently.
     */
    public InterferenceGraph(int countRegs) {
        interference = new ArrayList<IntSet>(countRegs);

        for (int i = 0; i < countRegs; i++) {
            interference.add(SetFactory.makeInterferenceSet(countRegs));
        }
    }

    /**
     * Adds a register pair to the interference/liveness graph. Parameter
     * order is insignificant.
     *
     * @param regV one register index
     * @param regW another register index
     */
    public void add(int regV, int regW) {
        ensureCapacity(Math.max(regV, regW) + 1);

        interference.get(regV).add(regW);
        interference.get(regW).add(regV);
    }

    /**
     * Dumps interference graph to stdout for debugging.
     */
    public void dumpToStdout() {
        int oldRegCount = interference.size();

        for (int i = 0; i < oldRegCount; i++) {
            StringBuilder sb = new StringBuilder();

            sb.append("Reg " + i + ":" + interference.get(i).toString());

            System.out.println(sb.toString());
        }
    }

    /**
     * Merges the interference set for a register into a given bit set
     *
     * @param reg {@code >= 0;} register
     * @param set {@code non-null;} interference set; will be merged
     * with set for given register
     */
    public void mergeInterferenceSet(int reg, IntSet set) {
        if (reg < interference.size()) {
            set.merge(interference.get(reg));
        }
    }

    /**
     * Ensures that the interference graph is appropriately sized.
     *
     * @param size requested minumum size
     */
    private void ensureCapacity(int size) {
        int countRegs = interference.size();

        interference.ensureCapacity(size);

        for (int i = countRegs; i < size; i++) {
            interference.add(SetFactory.makeInterferenceSet(size));
        }
    }
}
