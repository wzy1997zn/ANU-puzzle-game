package comp1110.ass2;

import org.junit.*;
import java.util.TreeSet;
import static org.junit.Assert.assertTrue;

public class SolveTest {
    private void test(String challange, String expected) {
        String out = FocusGame.solve(null, challange); // get solve result

        TreeSet<String> outSet = new TreeSet<>();
        // sort result
        for (int i = 0; i < out.length(); i += 4) {
            outSet.add(out.substring(i, i + 4));
        }
        String outstr = "";
        for (String o : outSet) {
            outstr += o;
        }

        // check if the result is true
        boolean found = false;

        if (outstr.equals(expected)) {
            found = true;
        }
        assertTrue("For objective " + challange + ", was expecting one of " + expected + ", but got " + outstr, found);
    }

    @Test
    public void test_solve() {
        test("RRRBWBBRB","a000b013c113d302e323f400g420h522i613j701");
        test("BGGWGGRWB","a100b210c220d400e003f801g030h502i733j332");
        test("RGGRGGRRB","a332b513c613d211e103f001g030h201i500j701");
        test("WBWWWWRWG","a500b012c703d232e000f320g300h522i030j322");
        test("WWRGWWGGW","a000b013c113d623e500f401g721h322i701j303");
        test("WWWWRWBBB","a000b013c113d600e330f410g721h300i531j412");
        test("WWRGWRWWR","a221b300c613d513e100f421g030h003i531j701");
        test("WGBWGBWGB","a111b013c230d621e513f311g500h201i000j701");
        test("GWWBWWGBG","a723b222c330d103e701f001g030h411i212j300");
        test("BWGWWWGWB","a022b510c130d532e622f411g110h000i303j500");
        test("RRRRRRRRR","a300b532c122d513e232f000g611h601i030j010");
        test("BBBWWBBBB","a030b102c613d432e402f120g420h000i231j701");
        test("GGGGGGGGG","a520b130c330d002e020f400g721h101i700j410");
        test("GGRGGRRRR","a030b532c202d513e232f001g611h601i102j112");
        test("WWWWWWWWW","a022b132c430d003e611f511g110h400i200j701");
        test("WRBWRBWRB","a003b601c430d413e022f300g100h122i523j701");
        test("BWRBRWBWR","a412b100c332d003e022f140g500h522i313j701");
    }
}
