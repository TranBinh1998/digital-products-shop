package vn.com.poly.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class RegisterUser {

    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min = 8, message = "độ dài tối thiểu là 8")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    private String password;

    @NotBlank(message = "Thông tin bắt buộc")
    private String rePassword;

    @NotBlank(message = "Thông tin bắt buộc")
    private String userName;

    @NotBlank(message = "Thông tin bắt buộc")
    private String hoVaTen;

    @NotBlank(message = "Thông tin bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;


    @NotBlank(message = "Thông tin bắt buộc")
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Số điện thoại không hợp lệ")
    private String soDienThoai;


    @NotBlank(message = "Thông tin bắt buộc")
    private String diaChi;

    public RegisterUser() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RegisterUser(@NotBlank(message = "Thông tin bắt buộc") @Size(min = 8, message = "độ dài tối thiểu là 8") @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt") String password, @NotBlank(message = "Thông tin bắt buộc") String rePassword, @NotBlank(message = "Thông tin bắt buộc") String userName, @NotBlank(message = "Thông tin bắt buộc") String hoVaTen, @NotBlank(message = "Thông tin bắt buộc") @Email(message = "Email không hợp lệ") String email, @NotBlank(message = "Thông tin bắt buộc") @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Số điện thoại không hợp lệ") String soDienThoai, @NotBlank(message = "Thông tin bắt buộc") String diaChi) {
        this.password = password;
        this.rePassword = rePassword;
        this.userName = userName;
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
