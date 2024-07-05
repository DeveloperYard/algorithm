select hr.DEPT_ID, DEPT_NAME_EN, AVG_SAL
from (
    select DEPT_ID, round(avg(sal)) as AVG_SAL
    from hr_employees
    group by dept_id
) as AA
left join hr_department hr on AA.dept_id = hr.dept_id
order by AVG_SAL desc;
