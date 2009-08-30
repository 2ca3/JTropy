<%@ page contentType="text/html;charset=UTF-8" language="java" %><html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="content-script-type" content="text/javascript" />
    <meta http-equiv="content-style-type" content="text/css" />
    <link rel="stylesheet" href="css/jtropy.css" type="text/css">
    <title>
    JTropy
    </title>
  </head>
  <body>
    <div class="main">
      <form action="/s" method="post">
        <table class="tbl">
          <tr>
            <td>
              <a href="/"><img alt="JTropy" src="images/logo.png" border="0" /></a>
            </td>
          </tr>
          <tr>
            <td>
              <div class="error">
                <%if (request.getAttribute("error") != null) out.print(request.getAttribute("error")); %>
              </div>
            </td>
          </tr>
          <tr>
            <td class="btn">
              <a href="/"><img alt="Random" src="images/random.png" border="0" /></a>
            </td>
          </tr>
          <tr>
            <td class="title">
              タイトル：
              <input type="text" name="title" value="<%if (request.getAttribute("title") != null) out.print(request.getAttribute("title")); %>" size="50" class="text">
            </td>
          </tr>
          <tr>
            <td class="body2">
              本文：
              <textarea name="body" cols="65" rows="10" class="area"><%if (request.getAttribute("body") != null) out.print(request.getAttribute("body")); %></textarea>
            </td>
          </tr>
          <tr>
            <td>
              <input type="hidden" name="key" value="<%if (request.getAttribute("key") != null) out.print(request.getAttribute("key")); %>">
              <input type="image" src="images/save.png" name="send" value="送信">
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>
