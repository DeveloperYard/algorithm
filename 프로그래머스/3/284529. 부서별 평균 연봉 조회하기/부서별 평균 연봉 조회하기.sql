select hd.DEPT_ID,  hd.dept_name_en, AA.AVG_SAL
from (
        select he.dept_id, round(avg(sal)) as AVG_SAL
        from hr_employees he
        group by dept_id
    ) as AA
left join hr_department hd on AA.dept_id = hd.dept_id
order by AVG_SAL desc;
