public class Test {

    public static void main(String[] args) {
        String str = "FPCPPCFFCPCPFFPPCCPFCPPCFFFCPCFFPCCFPPFPCFCFCFFFPPPPFPFFPCFFFPPCCFFFPFFPPCFPPPFPCPCFCFCCPPPPCPPCCPFF";
        str = str.replace('C', 'c');
        str = str.replace('F', '_');
        str = str.replace('P', '|');
        System.out.println(str);
    }

}
