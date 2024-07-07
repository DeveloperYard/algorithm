-- 코드를 작성해주세요
with CODES AS (
    select
        name, category, code
    from skillcodes
    where category = 'Front End'
)


select 
    ID,
    EMAIL,
    FIRST_NAME,
    LAST_NAME
from developers d
where exists (
    select 1
    from CODES c
    where d.skill_code & c.code = c.code
)
order by ID asc;