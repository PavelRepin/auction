<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table>
  <tr>
    <c:if test="${currentPage != 1}">
      <td>
        <form action="controller" method="post">
          <input type="hidden" name="command" value="goToLotsPage"/>
          <input type="hidden" name="pagination" value="${currentPage - 1}"/>
          <input type="submit" value="Prev" class="prev_next"/>
        </form>
      </td>
    </c:if>
    <c:forEach begin="1" end="${pages}" var="i">
      <c:choose>
        <c:when test="${currentPage eq i}">
          <td id="currentPage">${i}</td>
        </c:when>
        <c:otherwise>
          <td>
            <form action="controller" method="post">
              <input type="hidden" name="command" value="goToLotsPage"/>
              <input type="hidden" name="pagination" value="${i}"/>
              <input type="submit" value="${i}" class="other_button"/>
            </form>
          </td>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt pages}">
      <td>
        <form action="controller" method="post">
          <input type="hidden" name="command" value="goToLotsPage"/>
          <input type="hidden" name="pagination" value="${currentPage + 1}"/>
          <input type="submit" value="Next" class="prev_next"/>
        </form>
      </td>
    </c:if>
  </tr>
</table>