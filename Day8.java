import java.util.ArrayList;

public class Day8 {

    public static String[] antinodeRows;

    public static void printAntinodes() {
        for (String row: antinodeRows){
            System.out.println(row);
        }
    }

    public static void addAntinodes(ArrayList<Integer> freq1, ArrayList<Integer> freq2) {
        for (int n=1; n<=50; n++) {
            try {
                int newRow1 = freq1.get(0) - n*( freq2.get(0)-freq1.get(0) );
                int newCol1 = freq1.get(1) - n*( freq2.get(1)-freq1.get(1) );
                antinodeRows[newRow1] = antinodeRows[newRow1].substring(0,newCol1) + 'X'
                        + antinodeRows[newRow1].substring(newCol1+1);
            } catch (IndexOutOfBoundsException e) {}
            try {
                int newRow1 = freq2.get(0) + n*( freq2.get(0)-freq1.get(0) );
                int newCol1 = freq2.get(1) + n*( freq2.get(1)-freq1.get(1) );
                antinodeRows[newRow1] = antinodeRows[newRow1].substring(0,newCol1) + 'X'
                        + antinodeRows[newRow1].substring(newCol1+1);
            } catch (IndexOutOfBoundsException e) {}
        }

    }

    public static void main(String[] args){

        String input =".....y..........................p................r\n" +
                "........I.........................................\n" +
                "......................4.s.........................\n" +
                "..........4.......................................\n" +
                "....y.............................................\n" +
                "......................................p.........r.\n" +
                "..........0..s......N..................1.....p....\n" +
                "..y........4.......................p..............\n" +
                "...............0..................................\n" +
                "..............0....t....N....h....................\n" +
                ".............N....................................\n" +
                "......j...................s............H...l..O...\n" +
                "..........q.................H................O....\n" +
                "..f...e.qj.....y...0..............................\n" +
                "...........t..........................k..Q..r.....\n" +
                ".........6................Q..s...x......W.........\n" +
                "....2..b...e....t..4.........c.....xW.j...........\n" +
                "...e....................w................1.....O..\n" +
                "..e..j..5...........................c.............\n" +
                ".........B..2...............MK................H...\n" +
                "...2......b...g..X...q..........h...............O.\n" +
                "...q...2..........m....k...i...............QV.x...\n" +
                "...................i.........W.k.............HQ...\n" +
                "........b...X...............D..........c...N......\n" +
                "................................l..........h.....I\n" +
                ".m...........g......l.......c.............3......V\n" +
                "....X.......m........g...V.K...7......F.d.........\n" +
                ".........b.X...U..........................C.......\n" +
                ".....................l..............o.1....C......\n" +
                "............u.............K..............3...d....\n" +
                "......................i.T....f................V...\n" +
                "..............................1.k.................\n" +
                ".B.....E......9..m....K..5.M......................\n" +
                "...P...............M...95....o..i........I........\n" +
                "...............................S......3......wI...\n" +
                ".....EP...........9........5..T.R.................\n" +
                ".P..........v..9......f.............R.Co..w3......\n" +
                "..........h...SG..v.E...7..f.T....................\n" +
                "..........6..........L.................Y.......d..\n" +
                "..........B...............U........D..............\n" +
                "....B................U.....8..M....n...J..........\n" +
                ".........................L................Fw......\n" +
                "....L6E.P.................7.UG....J.....Y.D.......\n" +
                "........t........v...SJ........n..d...............\n" +
                "......................8v.....uG...................\n" +
                "..................L.....n.........................\n" +
                "...............T..............n......D............\n" +
                "..............o.........8................J.Y.R....\n" +
                "..................S...............u....F.......R..\n" +
                "........6..............u.....7.8..........Y..F....";

        String[] rows = input.split("\\R+");
        ArrayList<Character> uniqueFreq = new ArrayList<Character>();
        ArrayList<ArrayList<ArrayList<Integer>>> indexes = new ArrayList<ArrayList<ArrayList<Integer>>>();

        for (int i=0; i<rows.length; i++) {
            for (int j=0; j<rows[i].length(); j++) {
                char thisChar = rows[i].charAt(j);
                if (thisChar == '.') { continue;}
                if (!uniqueFreq.contains(thisChar)) {
                    uniqueFreq.add(thisChar);
                    indexes.add( new ArrayList<ArrayList<Integer>>() );
                }
                ArrayList<Integer> thisCharIndex = new ArrayList<Integer>();
                thisCharIndex.add(i);
                thisCharIndex.add(j);

                int index = uniqueFreq.indexOf(thisChar);

                ArrayList<ArrayList<Integer>> newCharIndexes = indexes.get(index);
                newCharIndexes.add(thisCharIndex);

                indexes.set(index, newCharIndexes);
            }
        }

        antinodeRows = rows.clone();
        for (int i=0; i<antinodeRows.length; i++) {
            for (int j=0; j<antinodeRows[i].length(); j++) {
                if (antinodeRows[i].charAt(j) != '.') {
                    antinodeRows[i] = antinodeRows[i].substring(0,j) + 'X' + antinodeRows[i].substring(j+1);
                }
            }
        }

        for (int i=0; i<uniqueFreq.size(); i++) {

            if (indexes.get(i).size() == 3) {
                ArrayList<Integer> freq1 = indexes.get(i).get(0);
                ArrayList<Integer> freq2 = indexes.get(i).get(1);
                ArrayList<Integer> freq3 = indexes.get(i).get(2);
                addAntinodes(freq1, freq2);
                addAntinodes(freq1, freq3);
                addAntinodes(freq2, freq3);
            } else if (indexes.get(i).size() ==4) {
                ArrayList<Integer> freq1 = indexes.get(i).get(0);
                ArrayList<Integer> freq2 = indexes.get(i).get(1);
                ArrayList<Integer> freq3 = indexes.get(i).get(2);
                ArrayList<Integer> freq4 = indexes.get(i).get(3);
                addAntinodes(freq1, freq2);
                addAntinodes(freq1, freq3);
                addAntinodes(freq1, freq4);
                addAntinodes(freq2, freq3);
                addAntinodes(freq2, freq4);
                addAntinodes(freq3, freq4);
            } else {
                System.out.println("Cannot calculate differences for " + indexes.get(i).size()
                        + " number of frequencies");
            }
        }
        printAntinodes();
        int xCount =0;
        for (int i=0; i<antinodeRows.length; i++) {
            for (int j=0; j<antinodeRows[i].length(); j++) {
                if (antinodeRows[i].charAt(j) == 'X') {
                    xCount++;
                }
            }
        }
        System.out.println("There are " + xCount + " Xs");



    }
}
