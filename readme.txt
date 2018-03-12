    select F001D as f003d0042,
    F004V as f008v0042,
    F005N as f007n0042,
    RECTIME as obRectime0042,
    TEXTID as obTextId0042,
    RECTIME as f002d0042,
    F002V as f004v0042,
    OB_SECCODE_0045 as obSeccode0045
    F003V as f006b0042
    from (select rownum rn,F001D, F004V,F005N,RECTIME,TEXTID,F002V,OB_SECCODE_0045
          from (select
                 max(F001D) F001D,
                 max(F004V) F004V,
                 max(F005N) F005N,
                 max(RECTIME) RECTIME,
                 max(F003V) F003V ,
                 TEXTID,
                 max(F002V) F002V,
                 WM_CONCAT(DISTINCT OB_SECCODE_0045) OB_SECCODE_0045
                 FROM juchao.info3015 a,TB_TEXT_0045 b
                 WHERE  a.RECTIME >=  to_date('2015-01-01 00:00:00','yyyy-mm-dd hh24:mi:ss') and a.RECTIME <  to_date('2015-02-01 00:00:00','yyyy-mm-dd hh24:mi:ss')
                 and a.TEXTID=b.OB_TEXTID_0045
                 and F001V_0045 IN ('QDII','LOF','ETF','老基金','开放式基金','封闭式基金')
                 and b.ob_seccode_0045 is not null
                 group by TEXTID
                 order by TEXTID
             )
         )

         dfsdfasdf