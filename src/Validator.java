import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

public class Validator {
    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            check(login, password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void check(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (login.length() > 20) throw new WrongLoginException("Длина логина не должна быть больше 20");
        if (password.length() > 20) throw new WrongPasswordException("Длина пароля не должна быть больше 20");
        if (!(checkWord(login))) throw new WrongLoginException("Логин содержит недопустимые символы");
        if (!(checkWord(password))) throw new WrongLoginException("Пароль содержит недопустимые символы");
        if (!(password.equals(confirmPassword))) throw new WrongPasswordException("Пароли не совпадают");
    }

    // Проверка слова на недопустимые символы, при наличии оного возвращается false
    public static boolean checkWord(String word) {
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            if (!checkSymb(word.charAt(i))) return false;
        }
        return true;
    }

    // Проверка очередного символа из слова на допустимость, возвращает true если символ является таковым
    public static boolean checkSymb(char symb) {
        return (symb >= 'a' && symb <= 'z') || symb == '_' || Character.isDigit(symb);
    }
}
