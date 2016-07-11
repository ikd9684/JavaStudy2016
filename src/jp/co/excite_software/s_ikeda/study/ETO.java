package jp.co.excite_software.s_ikeda.study;

public class ETO {

    public enum 十二支 {
        子("ね"),
        丑("うし"),
        寅("とら"),
        卯("う"),
        辰("たつ"),
        巳("み"),
        午("うま"),
        未("ひつじ"),
        申("さる"),
        酉("とり"),
        戌("いぬ"),
        亥("い"),
        ;
        // 日本語を使うのは遊びすぎなので実業務ではNG

        private String kana;
        private 十二支(String kana) {
            this.kana = kana;
        }
        public String toString() {
            return String.format("%s（%s）", super.toString(), this.kana);
        }
    }

    public String getEto(int year) {
        return get十二支(year).toString();
    }

    public 十二支 get十二支(int year) {
        return 十二支.values()[(year + 8) % 12];
    }

    public static void main(String[] args) {

        ETO eto = new ETO();

        System.out.println(eto.getEto(2016));
        System.out.println(eto.getEto(1976));
    }
}
