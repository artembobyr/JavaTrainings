public class TestWithKeywords {
    public static void main(String[] args) {
        String path = (System.getProperty("user.dir") + "\\KDD\\keywords.xlsx");
        System.out.println(path);
        ExcelHelper.setExcel(path, 0);
        for (int i = 0; i < 8; i++) {
            String keyword = ExcelHelper.getData(i, 1);
            if (keyword.equals("openBrowser")) {
                ActionKeywords.openBrowser();
            } else if (keyword.equals("openURL")) {
                ActionKeywords.openURL();
            } else if (keyword.equals("clickLoginButton")) {
                ActionKeywords.clickLoginButton();
            } else if (keyword.equals("inputValidLogin")) {
                ActionKeywords.inputValidLogin();
            } else if (keyword.equals("inputValidPassword")) {
                ActionKeywords.inputValidPassword();
            } else if (keyword.equals("clickEnterButton")) {
                ActionKeywords.clickEnterButton();
            } else if (keyword.equals("closeBrowser")) {
                ActionKeywords.closeBrowser();
            }
        }
    }
}

