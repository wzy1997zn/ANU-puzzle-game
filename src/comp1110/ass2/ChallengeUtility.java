package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;

public class ChallengeUtility {

    public static final Solution[] SOLUTIONS = {
            new Solution("RRRBWBBRB",
                    "a000b013c113d302e323f400g420h522i613j701"),
            new Solution("RWWRRRWWW",
                    "a701b400c410d303e111f330g030h000i733j332"),
            new Solution("BGGWGGRWB",
                    "a100b210c220d400e003f801g030h502i733j332"),
            new Solution("WRRWRRGWW",
                    "a123b232c222d613e400f011g000h522i200j701"),
            new Solution("GWRGWWGGG",
                    "a513b130c502d002e020f401g721h101i713j332"),
            new Solution("GRWGRWWWW",
                    "a701b132c430d403e111f511g620h101i603j003"),
            new Solution("RGGRGGRRB",
                    "a332b513c613d211e103f001g030h201i500j701"),
            new Solution("GGGRGRBBB",
                    "a723b013c202d120e422f440g000h601i232j410"),
            new Solution("RGGGGRBGG",
                    "a200b230c430d711e600f011g000h503i133j112"),
            new Solution("BBBWRWGGG",
                    "a630b130c330d002e020f100g301h601i502j412"),
            new Solution("WBWWWWRWG",
                    "a500b012c703d232e000f320g300h522i030j322"),
            new Solution("BBGRWBRRB",
                    "a701b532c402d230e210f140g420h100i612j003"),
            new Solution("WWRGWWGGW",
                    "a000b013c113d623e500f401g721h322i701j303"),
            new Solution("WRRRRRWWW",
                    "a210b232c703d420e500f001g120h522i030j100"),
            new Solution("WWWWRWBBB",
                    "a000b013c113d600e330f410g721h300i531j412"),
            new Solution("GBBRRRBBG",
                    "a012b202c330d512e222f600g030h611i630j000"),
            new Solution("BRRBWRBBB",
                    "a532b111c400d520e330f300g210h000i030j701"),
            new Solution("GGGWGWGGB",
                    "a622b322c202d232e021f540g110h601i003j410"),
            new Solution("GWRGGWGGG",
                    "a403b130c330d002e020f520g500h101i630j701"),
            new Solution("WWRGWRWWR",
                    "a221b300c613d513e100f421g030h003i531j701"),
            new Solution("WWRGGWGGW",
                    "a403b332c703d621e111f500g030h000i511j303"),
            new Solution("RBGWBBGWR",
                    "a021b503c703d621e301f340g110h000i603j230"),
            new Solution("BGWBWRBBB",
                    "a532b111c302d520e330f510g210h000i030j701"),
            new Solution("BGRWWWWWW",
                    "a000b013c302d510e623f521g120h222i210j701"),
            new Solution("WBWWBWGGG",
                    "a723b130c330d613e100f120g310h601i400j003"),
            new Solution("WGBWGBWGB",
                    "a111b013c230d621e513f311g500h201i000j701"),
            new Solution("BBBGGGWWW",
                    "a021b512c502d003e102f330g721h220i402j332"),
            new Solution("GBBGWWGBB",
                    "a723b202c002d012e701f140g501h422i422j022"),
            new Solution("BWGGWGGWB",
                    "a000b513c613d400e013f411g201h323i131j701"),
            new Solution("WBWGGBWGW",
                    "a221b301c430d600e713f011g511h001i400j113"),
            new Solution("GWGGWBGGG",
                    "a610b130c330d002e020f401g721h101i520j500"),
            new Solution("BGGWGGGWB",
                    "a621b210c130d022e001f811g430h402i703j200"),
            new Solution("GWWGWBGGG",
                    "a621b130c330d002e020f401g500h101i512j701"),
            new Solution("GBGWWBWWG",
                    "a723b310c330d103e701f001g030h213i520j300"),
            new Solution("GWWBWWGBG",
                    "a723b222c330d103e701f001g030h411i212j300"),
            new Solution("BGBWWWWBW",
                    "a532b410c002d213e013f801g620h122i433j400"),
            new Solution("GGWWGGBWG",
                    "a003b501c102d600e713f120g030h523i230j311"),
            new Solution("WBWWWWGGG",
                    "a723b130c330d011e701f320g300h000i213j501"),
            new Solution("BBBBGGBGB",
                    "a622b412c002d532e302f011g111h601i221j132"),
            new Solution("BBBWWWBGB",
                    "a600b122c001d532e101f520g400h612i302j132"),
            new Solution("WGGBGGGBW",
                    "a010b413c130d600e713f000g211h301i022j613"),
            new Solution("GBGWWWBGB",
                    "a600b310c202d532e013f520g000h612i222j132"),
            new Solution("BWGWWWGWB",
                    "a022b510c130d532e622f411g110h000i303j500"),
            new Solution("BGBBGGWBB",
                    "a000b220c703d621e430f011g201h123i510j400"),
            new Solution("BGBBWBBGB",
                    "a601b532c302d011e513f801g210h000i222j132"),
            new Solution("WWGBBWWBW",
                    "a000b200c711d121e013f521g311h601i331j511"),
            new Solution("WBWGGBBGW",
                    "a112b301c711d013e130f000g511h601i400j332"),
            new Solution("BWWBWGBWW",
                    "a503b232c711d521e211f401g011h601i133j000"),
            new Solution("RRRRRRRRR",
                    "a300b532c122d513e232f000g611h601i030j010"),
            new Solution("RRRRRWRWW",
                    "a412b601c111d221e300f011g530h001i432j701"),
            new Solution("RWWWRWWWR",
                    "a301b611c703d132e432f230g110h500i023j000"),
            new Solution("BWBBWBBBB",
                    "a132b601c430d011e211f300g410h000i422j701"),
            new Solution("BWWBBBBWB",
                    "a021b501c711d232e211f001g430h601i412j100"),
            new Solution("WWBWWBWWB",
                    "a003b601c430d300e022f411g100h122i523j701"),
            new Solution("BWBBBBWWW",
                    "a021b232c711d213e503f001g311h601i531j100"),
            new Solution("BBBBWBBWB",
                    "a021b102c613d223e402f440g420h000i333j701"),
            new Solution("BBBBWBBBB",
                    "a021b102c430d223e402f711g321h000i523j701", "a021b102c430d223e402f801g321h000i523j703", "a132b611c703d432e211f500g420h100i402j003", "a500b103c703d200e513f140g210h522i330j003"),
            new Solution("BBBWWBWBB",
                    "a013b102c613d432e402f000g420h122i121j701"),
            new Solution("WBBWBWWBW",
                    "a021b532c601d300e701f321g310h000i433j111"),
            new Solution("BBBWWBBBB",
                    "a030b102c613d432e402f120g420h000i231j701"),
            new Solution("WGGWWGWWW",
                    "a111b013c002d400e701f311g620h222i632j411"),
            new Solution("GGGGWGWWG",
                    "a711b502c302d020e000f421g030h523i622j211"),
            new Solution("GGWWGGWWG",
                    "a003b501c102d600e713f321g030h523i120j311"),
            new Solution("WBBWWBWWW",
                    "a022b420c430d711e600f001g401h122i213j100"),
            new Solution("BBBBWWBBB",
                    "a021b102c613d223e402f440g321h000i522j701"),
            new Solution("WBBBWWBWW",
                    "a021b410c100d232e621f400g220h000i532j701"),
            new Solution("WWRWRWWWR",
                    "a423b300c001d510e100f540g520h122i121j701"),
            new Solution("RRWWRWWRR",
                    "a230b100c300d003e432f120g030h601i733j412"),
            new Solution("WWWRRWWRR",
                    "a230b300c002d212e432f510g030h521i012j701"),
            new Solution("WGGGGGGGW",
                    "a000b502c302d711e123f011g530h323i213j420"),
            new Solution("WGGGGGWWW",
                    "a003b532c601d232e701f330g100h301i030j112"),
            new Solution("GGGGGGGGG",
                    "a520b130c330d002e020f400g721h101i700j410"),
            new Solution("GGRBGRBBR",
                    "a613b010c703d411e221f500g030h201i003j432"),
            new Solution("BGBGBRGRR",
                    "a013b201c302d422e510f240g121h522i000j701"),
            new Solution("WBWWRWWGW",
                    "a030b500c230d711e100f311g530h003i403j412"),
            new Solution("GGBRRGBRG",
                    "a622b302c102d321e130f801g601h523i002j020"),
            new Solution("GWWWRWWWB",
                    "a022b132c202d003e630f410g110h601i431j412"),
            new Solution("BBRWBBGWB",
                    "a630b103c130d200e412f801g430h501i210j003"),
            new Solution("BGWWBGWWW",
                    "a000b320c302d121e013f340g530h511i210j701"),
            new Solution("GWBGGWWGG",
                    "a600b213c430d711e003f520g030h101i502j403"),
            new Solution("BGGBGBBGG",
                    "a123b230c430d711e512f011g000h201i211j500"),
            new Solution("GGRGGRRRR",
                    "a030b532c202d513e232f001g611h601i102j112"),
            new Solution("GWGGWRGGG",
                    "a511b130c330d002e020f401g721h101i713j500"),
            new Solution("RRWGRRGGR",
                    "a030b500c230d403e521f020g721h111i713j000"),
            new Solution("GRRGGGRRG",
                    "a621b412c330d222e400f001g030h101i113j701"),
            new Solution("WRWWRWGWG",
                    "a013b500c411d132e711f540g520h300i000j203"),
            new Solution("RGGRWGGRR",
                    "a311b103c130d620e200f540g500h513i700j003"),
            new Solution("GGGGGGRWR",
                    "a003b020c102d620e132f540g500h513i700j311"),
            new Solution("GGGRRRWRW",
                    "a701b402c202d212e630f240g011h520i003j130"),
            new Solution("GRGWRRRWG",
                    "a532b502c411d520e132f811g110h000i021j300"),
            new Solution("RBRRBBRRB",
                    "a332b103c711d313e412f001g030h601i530j200"),
            new Solution("RRBWBRWWR",
                    "a600b132c002d210e432f120g400h013i733j512"),
            new Solution("WBWWRWWBW",
                    "a532b012c502d711e000f030g300h122i433j412"),
            new Solution("WWWRBRRBR",
                    "a132b300c002d523e321f011g111h511i632j701"),
            new Solution("RWWRWBWWB",
                    "a000b400c703d303e013f140g420h213i612j432"),
            new Solution("RBRRBRRBR",
                    "a012b532c032d513e311f240g611h601i003j200"),
            new Solution("WWWWWWWWW",
                    "a022b132c430d003e611f511g110h400i200j701"),
            new Solution("RRBBBBGGB",
                    "a100b130c711d011e212f001g400h601i523j332"),
            new Solution("GGGBBGRBG",
                    "a630b402c202d432e220f011g000h123i701j520"),
            new Solution("GBGRRRBRB",
                    "a332b202c711d320e003f100g030h601i222j511"),
            new Solution("GBRGBRGGG",
                    "a500b422c703d020e403f000g030h323i631j002"),
            new Solution("GBWRBBRRG",
                    "a120b013c202d501e232f540g000h512i413j701"),
            new Solution("WWBWGWWWR",
                    "a420b132c430d600e713f210g400h100i223j003"),
            new Solution("BGGRWBBGG",
                    "a030b230c210d003e621f440g101h611i303j500"),
            new Solution("BGRBGRGGG",
                    "a630b130c330d502e013f600g000h201i211j512"),
            new Solution("BRWRRRWRB",
                    "a402b532c001d320e101f240g611h601i303j130"),
            new Solution("BBBRRBWRB",
                    "a230b601c430d212e302f001g030h100i523j701"),
            new Solution("BWRBBWWBB",
                    "a621b102c501d432e022f140g311h000i602j701"),
            new Solution("RBBRBBRRR",
                    "a100b111c711d313e420f001g400h601i030j332"),
            new Solution("RWWBWWBBR",
                    "a021b611c703d223e432f000g321h500i300j010"),
            new Solution("RBBRWBRRB",
                    "a532b601c111d303e232f011g401h001i523j701"),
            new Solution("BBRRRRBBR",
                    "a000b013c122d411e202f721g500h422i231j701"),
            new Solution("RBBBWWBWR",
                    "a132b410c332d110e020f540g620h601i403j000"),
            new Solution("BWRBWRBWR",
                    "a611b103c430d513e201f001g030h400i333j701"),
            new Solution("BBBWRWRRR",
                    "a111b013c002d330e630f240g301h601i502j412"),
            new Solution("BBBRWBWRB",
                    "a323b102c613d121e402f440g420h000i021j701"),
            new Solution("RWBWWWBWR",
                    "a132b532c332d600e200f420g610h103i401j003"),
            new Solution("WRBWRBWRB",
                    "a003b601c430d413e022f300g100h122i523j701"),
            new Solution("BWRBRWBWR",
                    "a412b100c332d003e022f140g500h522i313j701"),
            new Solution("BRBBRBBWB",
                    "a021b102c502d223e411f811g611h000i333j432")
    };

    static final String[] BAD_PIECES = {
            "a719",
            "z103",
            "a01a",
            "a911",
            "a660",
            "A700",
            "i3C1",
    };

    static final String[] OFF_BOARD_1 = {
            "c030",
            "a721",
            "b602",
            "d710",
            "e632",
    };

    static final String[] OFF_BOARD_2 = {
            "j600",
            "f642",
            "f643",
            "h020",
            "i031",
    };

    static final String[] OVERLAP = {
            "a023c103d003",
            "a403j502h320",
            "j501a403e423",
            "e523a403j501",
    };

    static final ViablePlacement[] VP_LAST = {
            new ViablePlacement("a000c113d302e323f400g420h522i613j701",
                    "RRRBWBBRB",
                    0,
                    1,
                    "b013"),
            new ViablePlacement("a000c113d302e323f400g420h522i613j701",
                    "RRRBWBBRB",
                    1,
                    3,
                    "b013"),
            new ViablePlacement("a000c113d302e323f400g420h522i613j701",
                    "RRRBWBBRB",
                    1,
                    4,
                    "b013"),
            new ViablePlacement("a000b013d302e323f400g420h522i613j701",
                    "RRRBWBBRB",
                    1,
                    2,
                    "c113"),
            new ViablePlacement("a000b013d302e323f400g420h522i613j701",
                    "RRRBWBBRB",
                    2,
                    2,
                    "c113"),
            new ViablePlacement("a100b130c711d011e212f001h601i523j332",
                    "RRBBBBGGB",
                    4,
                    0,
                    "g400g402"),
            new ViablePlacement("a100b130c711d011e212f001h601i523j332",
                    "RRBBBBGGB",
                    5,
                    1,
                    "g400g402"),
            new ViablePlacement("a100b130c711d011e212g402h601i523j332",
                    "RRBBBBGGB",
                    0,
                    0,
                    "f001f003"),
            new ViablePlacement("a100b130c711d011e212g402h601i523j332",
                    "RRBBBBGGB",
                    0,
                    2,
                    "f001f003"),
    };

    static final ViablePlacement[] VP_CENTRE = {
            new ViablePlacement("d302e132j020",
                    "RRRRRWRWW",
                    4,
                    2,
                    ""),
            new ViablePlacement("d302e132j020",
                    "RRRRRWRWW",
                    4,
                    3,
                    "i432f430f432"),
            new ViablePlacement("d302h322j020",
                    "RRRRRWRWW",
                    4,
                    2,
                    ""),
            new ViablePlacement("d302h322j020",
                    "RRRRRWRWW",
                    3,
                    3,
                    "a130"),
    };

    static final ViablePlacement[] VP_SIDES = {
            new ViablePlacement("j001",
                    "RRRRRWRWW",
                    0,
                    2,
                    "f011f013"),
            new ViablePlacement("j011",
                    "RRRRRWRWW",
                    0,
                    2,
                    ""),
            new ViablePlacement("h620",
                    "RRRRRWRWW",
                    8,
                    3,
                    "i733"),
            new ViablePlacement("j011",
                    "RRRRRWRWW",
                    0,
                    3,
                    ""),
    };

    static final ViablePlacement[] VP_REAL = {
            new ViablePlacement("",
                    "RRRBWBBRB",
                    0,
                    0,
                    "a000a003b001b003c001c002d000d002d003e000e001e003f000f001f002f003g000g002h000h001h003i000i002i003j000j001j003"),
            new ViablePlacement("a000b013c113d302",
                    "RRRBWBBRB",
                    3,
                    2,
                    "e323i323"),
            new ViablePlacement("a000b013c113d302e323",
                    "RRRBWBBRB",
                    4,
                    0,
                    "f400f402h401"),
            new ViablePlacement("",
                    "RRWWRWWRR",
                    0,
                    0,
                    "a000a003b001b003c001c002d000d002d003e000e001e003f000f001f002f003g000g002h000h001h003i000i002i003j000j001j003"),
            new ViablePlacement("a000b013c113e300f321",
                    "RRWWRWWRR",
                    4,
                    2,
                    "j412"),
            new ViablePlacement("a000c232e300f120g030j412",
                    "RRWWRWWRR",
                    4,
                    4,
                    "b432"),
            new ViablePlacement("c001d102",
                    "RRWWRWWRR",
                    1,
                    3,
                    "a121f130f132g121g123g130g132i121i130i132i133j130"),
    };
}



class ViablePlacement {
    String objective;
    String start;
    int xLoc;
    int yLoc;
    String expected;

    ViablePlacement(String iStart,
                    String iObjective,
                    int iX, int iY,
                    String iExpected) {
        objective = iObjective;
        start = iStart;
        xLoc = iX;
        yLoc = iY;
        expected = iExpected;
    }
}
