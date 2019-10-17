package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String objective;
    public String placement;
    Set<String> placements;

    Solution(String iObjective, String p1, String p2, String p3, String p4) {
        objective = iObjective;
        placement = p1;
        placements = new HashSet<>();
        placements.add(p1);
        placements.add(p2);
        placements.add(p3);
        placements.add(p4);
    }

    Solution(String iObjective, String iPlacement) {
        objective = iObjective;
        placement = iPlacement;
        placements = new HashSet<>();
        placements.add(iPlacement);
    }
}