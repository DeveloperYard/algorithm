def sol(n, edges):
    ans = 0

    arr = [[0]*n for _ in range(n)]
    for edge in edges:
        start, end = edge
        arr[start-1][end-1] = 1

    for i in range(n):
        for j in range(i+1, n):
            for k in range(j+1, n):
                if arr[i][j] == 1 and arr[j][k] == 1 and arr[i][k] == 1:
                    ans += 1

    return ans


for tc in range(int(input())):
    n, m = map(int, input().split())
    edges = []
    for i in range(m):
        edges.append(list(map(int, input().split())))

    res = sol(n, edges)
    print(f'#{tc+1} {res}')
