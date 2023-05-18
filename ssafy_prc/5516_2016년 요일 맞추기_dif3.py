def sol(month, date):
    day_of_week = 4
    standard = 0
    month_days = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    for i in range(month-1):
        standard += month_days[i]

    standard += date
    standard -= 1 # 1일

    return (standard+day_of_week) % 7


for tc in range(int(input())):
    m, d = map(int, input().split())
    res = sol(m, d)
    print(f'#{tc+1} {res}')