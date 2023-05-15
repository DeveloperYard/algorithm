def sol(p, q):
    s1 = (1-p)*q  # 뒤집었을 확률 * 올바른 방향일 확률, 1번만 뒤집음
    s2 = p*(1-q)*q
    # 두 번 뒤집어야 하므로 첫 번째는 올바른 방향 * 두 번째는 뒤집은 상태이므로 꽂히지 않을 확률 * 꽂힐 확률

    return s1 < s2


for tc in range(int(input())):
    a, b = map(float, input().split())
    res = "YES" if sol(a, b) else "NO"
    print(f'#{tc+1} {res}')