package thuynvph30181.poly.duanmau.QuanLySach;

public class Sach {
    private int masach;
    private String tensach;
    private int giathue;
    private int maloai;
    private String tenloai;

    public Sach() {
    }



    public Sach(int masach, String tensach, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public int getMasach() {
        return masach;
    }

    public void setMassach(int massach) {
        this.masach = massach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
