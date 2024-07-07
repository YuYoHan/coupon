<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
    <link rel="shortcut icon" type="image/x-icon" href="${cp}/app/img/favicon.ico">
    <link rel="stylesheet" href="${cp}/css/air/airReserve.css">
    <link rel="stylesheet" href="${cp}/css/air/air.css">
    <link rel="stylesheet" href="${cp}/css/global/footer.css">
    <link rel="stylesheet" href="${cp}/css/air/airticket.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
    <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
    <title>마이리얼트립 :: 항공권</title>
</head>
<body>
<%@ include file="/app/global/header.jsp" %>
<%@ include file="air.jsp" %>
<main style="height: 110vh;">
    <section class="airMain">
        <div class="main-notice">
            <h2>항공권 공지사항</h2>
            <div class="notice-more">
                <a class="notice-more-btn">더보기</a>
            </div>
            <table class="air-board">
                <tbody class="rowlength">
                <tr>
                    <td class="air-tal">
						<span class="air-board-contents">
						[공지] 2022년 6월 1일 부 유류할증료 인상 예정 안내
						</span>
                    </td>
                    <td class="time">2022-05-27</td>
                </tr>
                <tr>
                    <td class="air-tal">
						<span class="air-board-contents">
						[공지] 티웨이항공(TW) 비즈니스 좌석 판매 안내
						</span>
                    </td>
                    <td class="time">2022-05-27</td>
                </tr>
                <tr>
                    <td class="air-tal">
						<span class="air-board-contents">
						[공지] 거리두기 격상에 따른 국내선 항공권 환불 요청 방법
						</span>
                    </td>
                    <td class="time">2022-05-27</td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
    <section class="airBanner">
        <%@ include file="/app/air/airBanner.jsp" %>
    </section>
</main>
<div class="default-components" id="footer-components">
    <footer id="footer" class="footer-container">
        <%@ include file="/app/global/footer.jsp" %>
    </footer>
</div>
</body>
<script src="${cp}/js/swiper_ReserveContainer.js"></script>
<script src="${cp}/js/chatbot.js"></script>
<script src="${cp}/js/designForInput.js"></script>

</html>