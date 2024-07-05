-- 코드를 작성해주세요
select hg.EMP_NO, he.EMP_NAME, (
    case
        when score >= 96 then 'S'
        when score >= 90 then 'A'
        when score >= 80 then 'B'
        else 'C'
    end
) as GRADE, (
    case
        when score >= 96 then SAL*0.2
        when score >= 90 then SAL*0.15
        when score >= 80 then SAL*0.1
        else 0
    end
) as BONUS
from hr_department hd
    left join hr_employees he on hd.dept_id = he.dept_id
    left join (
        select EMP_NO, avg(score) as score
        from hr_grade
        group by EMP_NO
    ) hg on he.emp_no = hg.emp_no
order by hg.emp_no
