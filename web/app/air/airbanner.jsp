<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이리얼트립 :: 항공권</title>
    <c:set var="cp" value="${pageContext.request.contextPath}"></c:set>
    <link rel="shortcut icon" type="image/x-icon" href="${cp}/app/img/favicon.ico">
    <link rel="stylesheet" href="${cp}/css/air/air.css">
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" /></head>
<body>
<div class="search_are">
    <div id="div_notice" class="statue_box6" style="display:none;"></div>
    <div id = "inttab">
        <ul class="section_1">
            <li class="interval_select">
                <%--@declare id="initformcode1"--%><input type="radio" id="initFormCodel" name="initform" onclick="javascript:airfn_clickinitFormCode_0100100010('RT', 'int');" checked="checked" value="RT" class="input_radio">
                <label for="initFormCode1">왕복</label>
            </li>
            <!-- 항공권 예약 내역  -->
            <li class="extra_link">
                <a href="비회원 내역보는 jsp 경로" target="_parent"  title="항공권 예약내역">비회원 예약내역</a>
            </li>
            <li class="extra_link">
                <a href="${cp}/mytrip/mytrip.mt" target="_parent" title="항공권 예약내역">항공권 예약내역</a>
            </li>
        </ul>
        <div class="section_multi" id="div_search1">
            <form id="frm" method = "get" action="${cp}/air/airSearch.ar">
                <fieldset id="div_search1_trip1">
                    <legend></legend>
                    <ul class="itinerary">
                        <li class="clearfix">
                            <div class="city_selector clearfix border_all">
                                <input id="depCtyCodeSearch"  placeholder="서울" class="input_text input" type="text" readonly="readonly">
                                <button type="button" class="btn_swapper" id="btn_arrDepChange">도착지 출발지 교체</button>
                                <input id="txt_arrCtyCode" placeholder="제주 (CJU)" class="input_text" type="text" readonly="readonly">
                            </div>
                            <div class="date_selector clearfix border_all ml8" id="div_day_selector" role="calendarPopTxt" style="cursor: pointer;">
                                <input id="txt_depDt" class="input_text" type="hidden" title="가는날 선택" placeholder="가는날 선택" readonly="readonly" value="">
                                <input id="txt_depDt_view" class="input_text" type="text" title="가는날 선택" placeholder="가는날 선택 - 오는날 선택" readonly="readonly" name="datefilter" role="calendarPopInput" style="cursor: pointer;" value="">
                            </div>
                            <div class="seat_wrap border_all">
                                <a class="select_border">
                                    <input name="count-total-input" class="count-total-input" type="hidden" readonly="readonly" value="">
                                    <span class="txt" id="bin_people">승객 <span class="count-total"></span>명, 전체</span>
                                </a>
                            </div>
                            <a class="btn_price_search" onclick="return ok_frm()">검색</a>
                            <div class="Nop_wrap hidden">
                                <div class="Nop_header">
                                    <h1>인원 & 좌석등급</h1>
                                </div>
                                <div class="Nop_container">
                                    <div class="Nop_items">
                                        <dl class="Nop_item">
                                            <dt>성인 <span>만 12세 이상</span></dt>
                                            <dd class="count-box">
                                                <button class="Nop_btn_minus" type="button"></button>
                                                <span class="num">0</span>
                                                <button class="Nop_btn_plus"  type="button"></button>
                                            </dd>
                                        </dl>

                                        <dl class="Nop_item">
                                            <dt>소아 <span>만 12세 미만</span></dt>
                                            <dd class="count-box">
                                                <button class="Nop_btn_minus"  type="button"></button>
                                                <span class="num">0</span>
                                                <button class="Nop_btn_plus"  type="button"></button>
                                            </dd>
                                        </dl>

                                        <dl class="Nop_item">
                                            <dt>유아 <span>24개월 미만</span></dt>
                                            <dd class="count-box">
                                                <button class="Nop_btn_minus"  type="button"></button>
                                                <span class="num">0</span>
                                                <button class="Nop_btn_plus"  type="button"></button>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>


                        </li>
                    </ul>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
<script>

    $(function() {

        $('input[name="datefilter"]').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });

        $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
            $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
        });

        $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });

    });

    // 버튼을 누르면 인원수가 변화하는 스크립트
    function countBtn(){
        const $wrap = $('.Nop_wrap'),
            $btnMinus = $wrap.find('.Nop_btn_minus'),
            $btnPlus = $wrap.find('.Nop_btn_plus');
        $cell = $wrap.find('.cell');

        $btnMinus.on('click', function(e){
            let $this = $(this);
            let num = $this.parent().find('.num').text();
            num --;
            if (num <= 0) {
                num = 0;
            }
            $this.parent().find('.num').text(num);
            totalFn();
        });
        $btnPlus.on('click', function(){
            let $this = $(this);
            let num = $this.parent().find('.num').text();
            num ++;
            if (9 <= num) {
                num = 9; // 최대 인원수         
            }
            $this.parent().find('.num').text(num);
            totalFn();
        });

        function totalFn(){
            var $total = $(".count-box")
            var total = 0;
            $total.find(".num").each(function(){
                total += Number($(this).text());
            });
            $(".count-total").text(total);
            $(".count-total-input").val(total);
        };
    }

    countBtn();

    const seat_wrap = document.getElementsByClassName("seat_wrap")[0];
    const Nop_wrap = document.getElementsByClassName("Nop_wrap")[0];
    seat_wrap.addEventListener('click', ()=>{
        Nop_wrap.classList.toggle('hidden');
    })

    function ok_frm() {
        document.getElementById("frm").submit();
    }



</script>
<script src="${cp}/js/myinfo.js"></script>

</html>