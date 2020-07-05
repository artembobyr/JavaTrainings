<form id="calculator" action="calculator" method="post">
    <p>
        <input name="first_number">
        <input name="second_number">

        <input type="submit" name = "+" value="add">
        <input type="submit" name = "-" value="minus">
        <input type="submit" name = "*" value="multiply">
        <input type="submit" name = "/" value="divide">
    </p>
    <p> ${invalid}</br>
    ${sum}</p>
</form>