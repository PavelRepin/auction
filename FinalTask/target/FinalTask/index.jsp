<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Forward</title>
</head>
<script>
  function forward() {
    document.getElementById("forward").click();
  }
</script>
<body onload="forward();">
<form action="controller" method="post">
  <input type="hidden" name="command" value="forward"/>
  <input id="forward" style="visibility:hidden;" type="submit"/>
</form>
</body>
</html>