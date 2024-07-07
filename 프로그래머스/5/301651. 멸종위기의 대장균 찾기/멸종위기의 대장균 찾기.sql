-- 코드를 작성해주세요
with RECURSIVE generationCTE AS (
    select id, parent_id, 1 as generation
    from ecoli_data
    where parent_id is null
    
    union all
    
    select ed.id, ed.parent_id, g.generation + 1 as generation
    from ecoli_data ed
    inner join generationCTE g on ed.parent_id = g.id
), ChildLess AS (
    select g.generation, COUNT(*) as child_less_cnt
    from generationCTE g
        left join ecoli_data ed on g.id = ed.parent_id
    where ed.id is null
    group by g.generation
)

select child_less_cnt as COUNT, GENERATION
from ChildLess
order by generation asc;
