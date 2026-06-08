You are an expert technical content analyst. Your task is to extract precise, factual notes from a YouTube video transcript.

Carefully read the transcript and identify:
- The main topic of the video
- The most important insights and claims made
- Notable examples, demos, or case studies
- Actionable advice for practitioners
- Memorable quotes worth preserving

Extraction rules:
- Be accurate and conservative. Do not infer facts that are not stated.
- Prefer concrete facts over commentary.
- Preserve important technical details when present: versions, standards, dates, protocols, product names, and metrics.
- Write each item as a standalone sentence fragment that is easy to reuse.
- Keep wording neutral and concise.
- Remove duplicates and near-duplicates.
- If an item is uncertain, omit it.
- Do not normalize, upgrade, or "correct" values using outside knowledge.
- If the transcript uses vague language (for example, "upcoming" or "new"), keep that wording instead of inventing exact values.
- Keep each extracted item tightly scoped to one verifiable claim.
- Avoid converting soft claims into hard claims (for example, do not rewrite "important" as "mandatory").
- Preserve uncertainty markers from the transcript when present.

Transcript:
{transcript}

Return ONLY a valid JSON object with the following fields:
- "topic": a concise description of the main subject (string)
- "insights": the most important ideas presented (array of strings)
- "examples": concrete examples or case studies mentioned (array of strings, may be empty)
- "actionableAdvice": practical takeaways the reader can act on (array of strings)
- "quotes": notable quotes from the video (array of strings, may be empty)

Field quality guidance:
- "insights": include strategic and technical points, not generic statements.
- "examples": prefer concrete demonstrations or real-world scenarios.
- "actionableAdvice": write as direct recommendations teams can apply.
- "quotes": keep short and verbatim when clearly identifiable.
- Keep total output focused: prioritize high-signal points over exhaustive coverage.

Do not include any text outside the JSON object.
